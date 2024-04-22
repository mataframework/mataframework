package com.github.mataframework.utils

import com.github.mataframework.app.Platform
import java.net.URL

object Constants {
    const val DEFAULT_ANDROID_APP = "android.apk"
    const val DEFAULT_IOS_APP = "ios.app"
    const val DEFAULT_ANDROID_VERSION = "8.1"
    const val DEFAULT_IOS_VERSION = "16.4"
    const val DEFAULT_IOS_DEVICE_NAME = "iPhone 14"
    const val DEFAULT_ELEMENT_POLLING_TIMEOUT = 7_000L
    const val DEFAULT_ELEMENT_POLLING_INTERVAL = 500L

    val DEFAULT_APPIUM_URL = URL("http://localhost:4723/")
    val DEFAULT_PLATFORM = Platform.ANDROID
}