package com.github.mataframework.app

import com.github.mataframework.exception.MataFrameworkException
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import org.openqa.selenium.WebDriverException

class App internal constructor(val driver: AppiumDriver<MobileElement>) : AutoCloseable {
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
