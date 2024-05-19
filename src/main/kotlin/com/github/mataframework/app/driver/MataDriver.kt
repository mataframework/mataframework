package com.github.mataframework.app.driver

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.screenrecording.CanRecordScreen

interface MataDriver<T> where T : AppiumDriver<MobileElement>, T : CanRecordScreen {
    /**
     * Appium driver factory method
     */
    fun buildDriver(retryCount: Int): T
}