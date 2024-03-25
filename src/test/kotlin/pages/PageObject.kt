package pages

import app.App
import app.Configuration
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

open class PageObject(val app: App) {

    private val defaultTimeout = Configuration.getDefaultTimeout()
    private val defaultSleep = Configuration.getDefaultSleep()

    protected val driver: AppiumDriver<MobileElement>? = app.driver
    protected val waitDriver = WebDriverWait(driver, defaultTimeout, defaultSleep)

    protected val app_id: String = choiceText("//TODO package app", "//TODO package app")

    companion object {

        fun by(android: By, ios: By): By {
            return if (Configuration.isAndroid()) android else ios
        }

        fun choiceText(android: String, ios: String): String {
            return if (Configuration.isAndroid()) android else ios
        }
    }

    /*
     * Elements actions
     */
    fun waitForElement(by: By, timeout: Long = defaultTimeout): MobileElement {
        waitDriver.withTimeout(Duration.ofMillis(timeout))
        return waitDriver.until(MobileExpectedConditions.visibilityOfElementLocated(by))
    }

    fun terminateApp() {
        driver!!.terminateApp(app_id)
    }

}