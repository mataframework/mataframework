package com.github.mataframework.app.driver

import com.github.mataframework.app.Configuration
import com.github.mataframework.exception.MataFrameworkException
import io.appium.java_client.MobileElement
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.remote.AutomationName
import io.appium.java_client.remote.MobileCapabilityType
import org.openqa.selenium.Platform
import org.openqa.selenium.SessionNotCreatedException
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.remote.DesiredCapabilities
import java.io.File
import java.io.IOException

class MataIosDriver(
    private val fullReset: Boolean
) : MataDriver<IOSDriver<MobileElement>> {
    override fun buildDriver(retryCount: Int): IOSDriver<MobileElement> {
        try {
            return IOSDriver(Configuration.getAppiumUrl(), getCapabilities())
        } catch (e: SessionNotCreatedException) {
            e.printStackTrace()
            if (retryCount > 0) {
                println("Failed to init iOS driver. Retry")
                return buildDriver(retryCount - 1)
            } else {
                throw MataFrameworkException(
                    "Failed to init iOS driver. Please check platform version and device name.\n" +
                            "To see available simulators run 'xcrun simctl list devices available'", e
                )
            }
        } catch (e: WebDriverException) {
            throw MataFrameworkException("Failed to init iOS driver. Please check if Appium is running", e)
        } catch (e: IOException) {
            throw MataFrameworkException("Failed to init iOS driver", e)
        }
    }

    private fun getCapabilities(): DesiredCapabilities {
        val appLocation = Configuration.getAppLocation()
        val appFile = File(appLocation)
        if (!appFile.exists()) {
            throw MataFrameworkException(
                "No $appLocation at project root.\n" +
                        "Please build App for android, and copy APP to ${appFile.absolutePath}"
            )
        }

        val capabilities = DesiredCapabilities()
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.IOS)
        capabilities.setCapability(MobileCapabilityType.NO_RESET, !fullReset)
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, Configuration.getIosVersion())
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Configuration.getIosDeviceName())
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST)
        capabilities.setCapability(MobileCapabilityType.APP, appFile.absolutePath)
        return capabilities
    }
}