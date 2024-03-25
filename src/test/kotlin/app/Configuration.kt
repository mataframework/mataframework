package app

object Configuration {
    private enum class Platform {
        ANDROID, IOS
    }

    private var platform = Platform.ANDROID
    private var androidVersion = "8.1"
    private var iosVersion = "16.4"
    private var iosDeviceName = "iPhone 14"
    private var androidAppId = ""
    private var iosAppId = ""
    private var elementPollingTimeout = 7_000L
    private var elementPollingInterval = 500L

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

        val newAndroidVersion = System.getProperty("androidVersion")
        if (newAndroidVersion != null && newAndroidVersion.isNotEmpty()) {
            androidVersion = newAndroidVersion
        }

        val newIosVersion = System.getProperty("iosVersion")
        if (newIosVersion != null && newIosVersion.isNotEmpty()) {
            iosVersion = newIosVersion
        }

        val newIosDeviceName = System.getProperty("iosDeviceName")
        if (newIosDeviceName != null && newIosDeviceName.isNotEmpty()) {
            iosDeviceName = newIosDeviceName
        }
    }
}
