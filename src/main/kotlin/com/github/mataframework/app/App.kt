package com.github.mataframework.app

import com.github.mataframework.exception.MataFrameworkException
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.screenrecording.CanRecordScreen
import io.qameta.allure.Allure
import org.openqa.selenium.OutputType
import org.openqa.selenium.WebDriverException
import java.util.*

class App<T> internal constructor( val driver: T) :
    AutoCloseable where T : AppiumDriver<MobileElement>, T : CanRecordScreen {
    private var closed = false

    fun startRecordingScreen() {
        driver.startRecordingScreen()
    }

    fun stopRecordingScreen(attachment: String) {
        val content = driver.stopRecordingScreen()
        Allure.addByteAttachmentAsync(attachment, "video/mp4", "mp4") {
            Base64.getDecoder().decode(content)
        }
    }

    fun <T> getScreenshotAs(outputType: OutputType<T>): T {
        return driver.getScreenshotAs(outputType)
    }

    override fun close() {
        if (closed) {
            throw MataFrameworkException("Closed already")
        }
        val appiumDriver = driver
        val appId = appIdProperty.get()
        try {
            appiumDriver.terminateApp(appId)
        } catch (e: WebDriverException) {
            e.printStackTrace()
        }
        try {
            appiumDriver.quit()
        } catch (e: WebDriverException) {
            e.printStackTrace()
        }
        closed = true
    }

    companion object {
        val appIdProperty: PlatformProperty<String> = PlatformProperty(
            "appPackage",
            "bundleId"
        )
    }
}
