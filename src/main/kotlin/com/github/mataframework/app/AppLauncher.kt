package com.github.mataframework.app

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement

class AppLauncher {
    private var app: App? = null
    private val retryCount: Int = 1

    fun launch(fullReset: Boolean): App {
        // TODO if last app is not closed

        val driver: AppiumDriver<MobileElement> = createDriver(fullReset)
        app = App(driver)

        return app!!
    }

    private fun createDriver(fullReset: Boolean): AppiumDriver<MobileElement> {
        return if (Configuration.isAndroid()) {
            AndroidDriver(fullReset).getAndroidDriver(retryCount)
        } else {
            IosDriver(fullReset).getIOSDriver(retryCount)
        }
    }
}