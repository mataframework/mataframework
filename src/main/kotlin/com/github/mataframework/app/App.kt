package com.github.mataframework.app

import com.github.mataframework.exception.MataFrameworkException
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.screenrecording.CanRecordScreen
import org.openqa.selenium.WebDriverException

class App<T> internal constructor(val driver: T) :
    AutoCloseable where T : AppiumDriver<MobileElement>, T : CanRecordScreen {
    private var closed = false

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
