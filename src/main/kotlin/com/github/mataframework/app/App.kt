package com.github.mataframework.app

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import org.openqa.selenium.WebDriverException

class App internal constructor(driver: AppiumDriver<MobileElement>?) : AutoCloseable {
    var driver: AppiumDriver<MobileElement>?

    init {
        this.driver = driver
    }

    override fun close() {
        val appiumDriver = driver ?: return
        val appId = appIdProperty.getValue()
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
        driver = null
    }

    companion object {
        val appIdProperty: PlatformProperty<String> = PlatformProperty(
            "appPackage",
            "bundleId"
        )
    }
}
