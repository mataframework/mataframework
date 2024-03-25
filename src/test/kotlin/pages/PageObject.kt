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
        /**
         * Choose one of options based on current testing platform
         *
         * @param T type of setting
         * @param android setting for Android platform
         * @param ios setting for iOS platform
         * @return one of passed option
         */
        fun <T> choose(android: T, ios: T): T {
            return if (Configuration.isAndroid()) android else ios
        }

    }

    /**
     * Looking for an element with locator [by].
     *
     * @param by - element locator
     * @param timeout - max awaiting timeout
     * @param interval - checking interval
     *
     * @exception org.openqa.selenium.TimeoutException if element not found for [timeout]
     * @return found element
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

    /**
     * Looking for an element with locator [by].
     * In case if element found invoke [org.openqa.selenium.remote.RemoteWebElement.click].
     *
     * @param by - element locator
     * @param timeout - max awaiting timeout
     * @param interval - checking interval
     *
     * @exception org.openqa.selenium.TimeoutException if element not found for [timeout]
     * @return found element
     */
    fun waitForElementAndClick(
        by: By,
        timeout: Long = elementPollingTimeout,
        interval: Long = elementPollingInterval
    ): MobileElement {
        val mobileElement = waitForElement(by, timeout, interval)
        mobileElement.click()
        return mobileElement
    }

}