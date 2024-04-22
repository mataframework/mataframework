package com.github.mataframework.app.driver

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement

interface MataDriver {
    /**
     * Appium driver factory method
     */
    fun buildDriver(retryCount: Int): AppiumDriver<MobileElement>
}