package app

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.remote.AutomationName
import io.appium.java_client.remote.MobileCapabilityType
import org.openqa.selenium.Platform
import org.openqa.selenium.SessionNotCreatedException
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.remote.DesiredCapabilities
import utils.Constants
import java.io.File
import java.io.IOException
import java.net.URL

class IosDriver(private val autoLaunch: Boolean) {
    fun getIOSDriver(retryCount: Int): AppiumDriver<MobileElement> {
        try {
            return IOSDriver(URL("http://localhost:4723/"), getCapabilities())
        } catch (e: SessionNotCreatedException) {
            e.printStackTrace()
            if (retryCount > 0) {
                println("Failed to init iOS driver. Retry")
                return getIOSDriver(retryCount - 1)
            } else {
                throw RuntimeException("Failed to init iOS driver. Please check platform version and device name.\n" +
                        "To see available simulators run 'xcrun simctl list devices available'", e)
            }
        } catch (e: WebDriverException) {
            throw RuntimeException("Failed to init iOS driver. Please check if Appium is running", e)
        } catch (e: IOException) {
            throw RuntimeException("Failed to init iOS driver", e)
        }
    }

    fun getCapabilities(): DesiredCapabilities {
        val appFile = File(Constants.IOS_APP)
        if (!appFile.exists()) {
            throw RuntimeException("No ${Constants.IOS_APP} at project root.\n" +
                    "Please build App for android, and copy APP to ${appFile.absolutePath}")
        }

        val capabilities = DesiredCapabilities()
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.IOS)
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, Configuration.getIosVersion())
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Configuration.getIosDeviceName())
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST)
        capabilities.setCapability(MobileCapabilityType.APP, appFile.absolutePath)
        return capabilities
    }
}