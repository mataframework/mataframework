package com.github.mataframework.app

object Configuration {

    private var platform = Platform.ANDROID
    private var androidVersion = "8.1"
    private var iosVersion = "16.4"
    private var iosDeviceName = "iPhone 14"
    private var androidAppId = ""
    private var iosAppId = ""
    private var elementPollingTimeout = 7_000L
    private var elementPollingInterval = 500L

    fun getPlatform(): Platform {
        return platform
    }

    fun isAndroid(): Boolean {
        return platform == Platform.ANDROID
    }

    fun isiOS(): Boolean {
        return platform == Platform.IOS
    }

    fun getAndroidVersion(): String {
        return androidVersion
    }

    fun getIosVersion(): String {
        return iosVersion
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

    fun getAppId(): String {
        return when (platform) {
            Platform.IOS -> iosAppId
            Platform.ANDROID -> androidAppId
        }
    }

    init {
        val newPlatform = System.getProperty("platform")
        if (newPlatform != null && newPlatform.isNotEmpty()) {
            platform = when (newPlatform.lowercase()) {
                "ios" -> Platform.IOS
                "android" -> Platform.ANDROID
                else -> throw IllegalArgumentException("Unknown platform: $newPlatform")
            }
        }

        System.getProperty("androidVersion")?.ifNotBlank { androidVersion = it }

        System.getProperty("iosVersion")?.ifNotBlank { iosVersion = it }

        System.getProperty("iosDeviceName")?.ifNotBlank { iosDeviceName = it }

        System.getProperty("androidAppId")?.ifNotBlank { androidAppId = it }

        System.getProperty("iosAppId")?.ifNotBlank { iosAppId = it }

        System.getProperty("elementPollingTimeout")?.ifNotBlank { elementPollingTimeout = it.toLong() }

        System.getProperty("elementPollingInterval")?.ifNotBlank { elementPollingInterval = it.toLong() }
    }

    private inline fun <T : CharSequence> T?.ifNotBlank(block: (T) -> Unit) {
        this?.takeIf { it.isNotBlank() }
            ?.also(block)
    }
}
