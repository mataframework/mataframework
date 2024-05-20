package com.github.mataframework.spec.option

data class OnFailOption(
    /**
     * Special permission "get_server_log" should be granted.
     * Check [https://appium.io/docs/en/2.0/guides/security/](https://appium.io/docs/en/2.0/guides/security/)
     */
    val saveServerLog: Boolean = false,
    val serverLogName: String = "Fail Server Log",
    val makeScreenshot: Boolean = true,
    val screenshotName: String = "Fail Screenshot"
)
