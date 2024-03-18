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
        if (driver != null) {
            try {
                if (Configuration.isiOS()) {
                    driver!!.terminateApp("//TODO")
                } else {
                    driver!!.terminateApp("//TODO")
                }
                driver!!.quit()
            } catch (e: WebDriverException) {
                e.printStackTrace()
            }
            driver = null
        }
    }
}
