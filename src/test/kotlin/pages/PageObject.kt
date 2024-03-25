package pages

import app.App
import app.Configuration
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

open class PageObject(val app: App) {

    private val elementPollingTimeout = Configuration.getElementPollingTimeout()
    private val elementPollingInterval = Configuration.getElementPollingInterval()

    protected val driver: AppiumDriver<MobileElement>? = app.driver
    protected val waitDriver = WebDriverWait(driver, elementPollingTimeout, elementPollingInterval)

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
    fun waitForElement(
        by: By,
        timeout: Long = elementPollingTimeout,
        interval: Long = elementPollingInterval
    ): MobileElement {
        return waitDriver
            .withTimeout(Duration.ofMillis(timeout))
            .pollingEvery(Duration.ofMillis(interval))
            .until(ExpectedConditions.visibilityOfElementLocated(by)) as MobileElement
    }

}