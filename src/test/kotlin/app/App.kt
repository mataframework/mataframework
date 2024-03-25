package app

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import org.openqa.selenium.WebDriverException

class App internal constructor(driver: AppiumDriver<MobileElement>?) : AutoCloseable {
    var driver: AppiumDriver<MobileElement>?

    init {
        this.driver = driver
    }

    override fun close() {
        val appiumDriver = driver ?: return
        try {
            appiumDriver.terminateApp(Configuration.getAppId())
            appiumDriver.quit()
        } catch (e: WebDriverException) {
            e.printStackTrace()
        }
        driver = null
    }
}
