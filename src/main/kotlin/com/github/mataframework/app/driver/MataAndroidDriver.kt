package com.github.mataframework.app.driver

import com.github.mataframework.app.Configuration
import com.github.mataframework.exception.MataFrameworkException
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.AutomationName
import io.appium.java_client.remote.MobileCapabilityType
import org.openqa.selenium.Platform
import org.openqa.selenium.SessionNotCreatedException
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.remote.DesiredCapabilities
import java.io.File
import java.net.MalformedURLException

class MataAndroidDriver(
    private val fullReset: Boolean
) : MataDriver<AndroidDriver<MobileElement>> {
    override fun buildDriver(retryCount: Int): AndroidDriver<MobileElement> {
        return try {
            AndroidDriver(Configuration.getAppiumUrl(), getCapabilities())
        } catch (e: SessionNotCreatedException) {
            e.printStackTrace()
            if (retryCount > 0) {
                println("Failed to init Android driver")
                buildDriver(retryCount - 1)
            } else {
                throw MataFrameworkException("Failed to init Android driver. Please check if an emulator is running", e)
            }
        } catch (e: WebDriverException) {
            throw MataFrameworkException("Failed to init Android driver. Please check if Appium is running", e)
        } catch (e: MalformedURLException) {
            throw MataFrameworkException("Failed to init Android driver", e)
        }
    }

    private fun getCapabilities(): DesiredCapabilities {
        val appLocation = Configuration.getAppLocation()
        val appFile = File(appLocation)
        if (!appFile.exists()) {
            throw MataFrameworkException(
                "No $appLocation at project root.\n" +
                        "Please build App for android, and copy APK to ${appFile.absolutePath}"
            )
        }
        val capabilities = DesiredCapabilities()
        capabilities.setCapability(MobileCapabilityType.APP, appFile.absolutePath)
        capabilities.setCapability(MobileCapabilityType.NO_RESET, !fullReset)
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2)
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID.toString().lowercase())
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator")
        return capabilities
    }
}