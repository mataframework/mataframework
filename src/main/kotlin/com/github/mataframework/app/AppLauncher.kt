package com.github.mataframework.app

import com.github.mataframework.app.driver.MataAndroidDriver
import com.github.mataframework.app.driver.MataIosDriver
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement

class AppLauncher {
    private var app: App? = null
    private val retryCount: Int = 1

    fun launch(fullReset: Boolean): App {
        // TODO if last app is not closed

        val driver: AppiumDriver<MobileElement> = createDriver(fullReset)
        val instance = App(driver)
        app = instance

        return instance
    }

    private fun createDriver(fullReset: Boolean): AppiumDriver<MobileElement> {
        val mataDriver = when (Configuration.getPlatform()) {
            Platform.ANDROID -> MataAndroidDriver(fullReset)
            Platform.IOS -> MataIosDriver(fullReset)
        }
        return mataDriver.buildDriver(retryCount)
    }
}