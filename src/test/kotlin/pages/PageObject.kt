package pages

import app.App
import app.Configuration
import app.PlatformProperty
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

open class PageObject(val app: App) {

    private val elementPollingTimeout = Configuration.getElementPollingTimeout()
    private val elementPollingInterval = Configuration.getElementPollingInterval()
    private val noop = { _: MobileElement -> }

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
     * Looking for an element with locator [byPlatformProperty].
     *
     * @param byPlatformProperty - element locator
     * @param timeout - max awaiting timeout
     * @param interval - checking interval
     * @param consumer - element handler
     *
     * @exception org.openqa.selenium.TimeoutException if element not found for [timeout]
     * @return self-reference
     */
    fun waitForElement(
            byPlatformProperty: PlatformProperty<By>,
            timeout: Long = elementPollingTimeout,
            interval: Long = elementPollingInterval,
            consumer: (MobileElement) -> Unit = noop
    ): PageObject {
        val by = byPlatformProperty.getValue()
        val mobileElement = waitDriver
                .withTimeout(Duration.ofMillis(timeout))
                .pollingEvery(Duration.ofMillis(interval))
                .until(ExpectedConditions.visibilityOfElementLocated(by)) as MobileElement
        consumer(mobileElement)
        return this
    }

    /**
     * Looking for an element with locator [byPlatformProperty].
     * In case if element found invoke [org.openqa.selenium.remote.RemoteWebElement.click].
     *
     * @param byPlatformProperty - element locator
     * @param timeout - max awaiting timeout
     * @param interval - checking interval
     *
     * @exception org.openqa.selenium.TimeoutException if element not found for [timeout]
     * @return self-reference
     */
    fun waitForElementAndClick(
            byPlatformProperty: PlatformProperty<By>,
            timeout: Long = elementPollingTimeout,
            interval: Long = elementPollingInterval
    ): PageObject {
        return waitForElement(byPlatformProperty, timeout, interval) {
            it.click()
        }
    }

    /**
     * Looking for an element with locator [byPlatformProperty].
     * In case if element found invoke [org.openqa.selenium.remote.RemoteWebElement.getText] will be returned.
     *
     * @param byPlatformProperty - element locator
     * @param timeout - max awaiting timeout
     * @param interval - checking interval
     * @param consumer - text handler
     *
     * @exception org.openqa.selenium.TimeoutException if element not found for [timeout]
     * @return self-reference
     */
    fun waitForElementAndGetText(
            byPlatformProperty: PlatformProperty<By>,
            timeout: Long = elementPollingTimeout,
            interval: Long = elementPollingInterval,
            consumer: (String) -> Unit
    ): PageObject {
        return waitForElement(byPlatformProperty, timeout, interval) {
            consumer(it.text)
        }
    }

    /**
     * Looking for an element with locator [byPlatformProperty].
     * In case if element found invoke [org.openqa.selenium.remote.RemoteWebElement.getAttribute] will be returned.
     *
     * @param byPlatformProperty - element locator
     * @param attributePlatformProperty - attribute key value
     * @param timeout - max awaiting timeout
     * @param interval - checking interval
     * @param consumer - text handler
     *
     * @exception org.openqa.selenium.TimeoutException if element not found for [timeout]
     * @return self-reference
     */
    fun waitForElementAndGetAttribute(
            byPlatformProperty: PlatformProperty<By>,
            attributePlatformProperty: PlatformProperty<String>,
            timeout: Long = elementPollingTimeout,
            interval: Long = elementPollingInterval,
            consumer: (String) -> Unit
    ): PageObject {
        val attributeKey = attributePlatformProperty.getValue()
        return waitForElement(byPlatformProperty, timeout, interval) {
            consumer(it.getAttribute(attributeKey))
        }
    }

    /**
     * Looking for an element with locator [byPlatformProperty].
     * In case if element found invoke [io.appium.java_client.MobileElement.setValue] and pass [input].
     *
     * @param byPlatformProperty - element locator
     * @param input - data to input
     * @param timeout - max awaiting timeout
     * @param interval - checking interval
     *
     * @exception org.openqa.selenium.TimeoutException if element not found for [timeout]
     * @return self-reference
     */
    fun waitForElementAndClickAndInput(
            byPlatformProperty: PlatformProperty<By>,
            input: String,
            timeout: Long = elementPollingTimeout,
            interval: Long = elementPollingInterval
    ): PageObject {
        return waitForElement(byPlatformProperty, timeout, interval) {
            it.click()
            it.sendKeys(input)
        }
    }

    /**
     * Looking for an element with locator [byPlatformProperty].
     * In case if element found invoke [io.appium.java_client.MobileElement.setValue] and pass [input].
     *
     * @param byPlatformProperty - element locator
     * @param input - data to input
     * @param timeout - max awaiting timeout
     * @param interval - checking interval
     *
     * @exception org.openqa.selenium.TimeoutException if element not found for [timeout]
     * @return self-reference
     */
    fun waitForElementAndInput(
            byPlatformProperty: PlatformProperty<By>,
            input: String,
            timeout: Long = elementPollingTimeout,
            interval: Long = elementPollingInterval
    ): PageObject {
        return waitForElement(byPlatformProperty, timeout, interval) {
            it.sendKeys(input)
        }
    }

}