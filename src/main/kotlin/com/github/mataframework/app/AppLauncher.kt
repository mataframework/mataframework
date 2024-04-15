package com.github.mataframework.app

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement

class AppLauncher {
    private var app: App? = null
    private val autoLaunch: Boolean = true
    private val retryCount: Int = 1

    fun launch(): App {
        // TODO if last app is not closed

        val driver: AppiumDriver<MobileElement> = createDriver()
        app = App(driver)

        return app!!
    }

    private fun createDriver(): AppiumDriver<MobileElement> {
        return if (Configuration.isAndroid()) {
            AndroidDriver(autoLaunch).getAndroidDriver(retryCount)
        } else {
            IosDriver(autoLaunch).getIOSDriver(retryCount)
        }
    }
}