package com.github.mataframework.app

import com.github.mataframework.exception.MataFrameworkException
import com.github.mataframework.utils.Constants
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.remote.AutomationName
import io.appium.java_client.remote.MobileCapabilityType
import org.openqa.selenium.Platform
import org.openqa.selenium.SessionNotCreatedException
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.remote.DesiredCapabilities
import java.io.File
import java.net.MalformedURLException
import java.net.URL

class AndroidDriver(private val autoLaunch: Boolean) {
    fun getAndroidDriver(retryCount: Int): AppiumDriver<MobileElement> {
        try {
            return io.appium.java_client.android.AndroidDriver(
                URL("http://localhost:4723/"), getCapabilities()
            )
        } catch (e: SessionNotCreatedException) {
            e.printStackTrace()
            if (retryCount > 0) {
                println("Failed to init Android driver")
                return getAndroidDriver(retryCount - 1)
            } else {
                throw MataFrameworkException("Failed to init Android driver. Please check if an emulator is running", e)
            }
        } catch (e: WebDriverException) {
            throw MataFrameworkException("Failed to init Android driver. Please check if Appium is running", e)
        } catch (e: MalformedURLException) {
            throw MataFrameworkException("Failed to init Android driver", e)
        }
    }

    fun getCapabilities(): DesiredCapabilities {
        val appFile = File(Constants.ANDROID_APP)
        if (!appFile.exists()) {
            throw MataFrameworkException("No ${Constants.ANDROID_APP} at project root.\n" +
                    "Please build App for android, and copy APK to ${appFile.absolutePath}")
        }
        val capabilities = DesiredCapabilities()
        capabilities.setCapability(MobileCapabilityType.APP, appFile.absolutePath)
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2)
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID.toString().lowercase())
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator")
        return capabilities
    }
}