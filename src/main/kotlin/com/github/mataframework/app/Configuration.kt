package com.github.mataframework.app

import com.github.mataframework.utils.Constants
import java.net.URL

object Configuration {

    private var platform = Constants.DEFAULT_PLATFORM
    private var appiumUrl = Constants.DEFAULT_APPIUM_URL
    private var iosDeviceName = Constants.DEFAULT_IOS_DEVICE_NAME
    private var elementPollingTimeout = Constants.DEFAULT_ELEMENT_POLLING_TIMEOUT
    private var elementPollingInterval = Constants.DEFAULT_ELEMENT_POLLING_INTERVAL

    private var appVersionProperty = PlatformProperty(
        Platform.ANDROID to Constants.DEFAULT_ANDROID_VERSION,
        Platform.IOS to Constants.DEFAULT_IOS_VERSION
    )

    private var appLocationProperty = PlatformProperty(
        Platform.ANDROID to Constants.DEFAULT_ANDROID_APP,
        Platform.IOS to Constants.DEFAULT_IOS_APP
    )

    fun getPlatform(): Platform {
        return platform
    }

    fun getPlatformVersion(): String {
        return appVersionProperty.get()
    }

    fun getAppiumUrl(): URL {
        return appiumUrl
    }

    fun isAndroid(): Boolean {
        return platform == Platform.ANDROID
    }

    fun isiOS(): Boolean {
        return platform == Platform.IOS
    }

    fun getAndroidVersion(): String {
        return appVersionProperty.get(Platform.ANDROID)
    }

    fun getIosVersion(): String {
        return appVersionProperty.get(Platform.IOS)
    }

    fun getAppVersion(): String {
        return appVersionProperty.get()
    }

    fun getAppLocation(): String {
        return appLocationProperty.get()
    }

    fun getIosDeviceName(): String {
        return iosDeviceName
    }

    fun getElementPollingTimeout(): Long {
        return elementPollingTimeout
    }

    fun getElementPollingInterval(): Long {
        return elementPollingInterval
    }

    init {
        "platform".ifSystemPropertyNotBlank {
            platform = when (it.lowercase()) {
                "ios" -> Platform.IOS
                "android" -> Platform.ANDROID
                else -> throw IllegalArgumentException("Unknown platform: $it")
            }
        }

        "appiumUrl".ifSystemPropertyNotBlank { appiumUrl = URL(it) }

        "androidVersion".ifSystemPropertyNotBlank { appVersionProperty.set(Platform.ANDROID, it) }

        "iosVersion".ifSystemPropertyNotBlank { appVersionProperty.set(Platform.IOS, it) }

        "androidAppLocation".ifSystemPropertyNotBlank { appLocationProperty.set(Platform.ANDROID, it) }

        "iosAppLocation".ifSystemPropertyNotBlank { appLocationProperty.set(Platform.IOS, it) }

        "iosDeviceName".ifSystemPropertyNotBlank { iosDeviceName = it }

        "elementPollingTimeout".ifSystemPropertyNotBlank { elementPollingTimeout = it.toLong() }

        "elementPollingInterval".ifSystemPropertyNotBlank { elementPollingInterval = it.toLong() }
    }

    private inline fun String.ifSystemPropertyNotBlank(block: (String) -> Unit) {
        System.getProperty(this)?.ifNotBlank(block)
    }

    private inline fun <T : CharSequence> T?.ifNotBlank(block: (T) -> Unit) {
        this?.takeIf { it.isNotBlank() }
            ?.also(block)
    }
}
