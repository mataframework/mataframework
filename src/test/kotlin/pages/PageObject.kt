package pages

import app.App
import app.Configuration
import app.PlatformProperty
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import org.openqa.selenium.By
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.interactions.PointerInput
import org.openqa.selenium.interactions.Sequence
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import pages.scroll.ScrollDirection
import pages.scroll.ScrollScreen
import java.lang.reflect.Type
import java.time.Duration

open class PageObject(val app: App) {

    private val elementPollingTimeout = Configuration.getElementPollingTimeout()
    private val elementPollingInterval = Configuration.getElementPollingInterval()
    private val noop = { _: MobileElement -> }
    private val noScroll = ScrollScreen(ScrollDirection.DOWN, 0, 0)

    protected val driver: AppiumDriver<MobileElement>? = app.driver
    protected val waitDriver = WebDriverWait(driver, elementPollingTimeout, elementPollingInterval)
    protected val finger = PointerInput(PointerInput.Kind.TOUCH, "finger")

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
            scrollScreen: ScrollScreen = noScroll,
            consumer: (MobileElement) -> Unit = noop
    ): PageObject {
        val by = byPlatformProperty.getValue()
        val mobileElement = lookupElement(by, timeout, interval, scrollScreen)
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

    fun waitForElementAndScroll(
            byPlatformProperty: PlatformProperty<By>,
            scrollDirection: ScrollDirection,
            times: Int,
            timeout: Long = elementPollingTimeout,
            interval: Long = elementPollingInterval
    ): PageObject {
        return waitForElement(byPlatformProperty, timeout, interval) {
            val location = it.location
            val x = location.x
            val y = location.y

            scroll(x, y, scrollDirection, times)
        }
    }

    private fun scroll(x: Int,
                       y: Int,
                       scrollDirection: ScrollDirection,
                       scrollAmount: Int,
                       timeout: Duration = Duration.ofMillis(600)) {
        val scroll = Sequence(finger, 0)
        val viewport = PointerInput.Origin.viewport()

        scroll.addAction(finger.createPointerMove(Duration.ZERO, viewport, x, y))
        scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
        when (scrollDirection) {
            ScrollDirection.UP -> {
                scroll.addAction(finger.createPointerMove(timeout, viewport, x, y + scrollAmount))
            }

            ScrollDirection.DOWN -> {
                scroll.addAction(finger.createPointerMove(timeout, viewport, x, y - scrollAmount))
            }

            ScrollDirection.LEFT -> {
                scroll.addAction(finger.createPointerMove(timeout, viewport, x + scrollAmount, y))
            }

            ScrollDirection.RIGHT -> {
                scroll.addAction(finger.createPointerMove(timeout, viewport, x - scrollAmount, y))
            }
        }
        scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))
        driver?.perform(listOf(scroll))
    }

    private fun lookupElement(by: By,
                              timeout: Long,
                              interval: Long,
                              scrollScreen: ScrollScreen): MobileElement {
        val scrollTimes = scrollScreen.scrollTimes
        val lookingTimeout = Duration.ofMillis(timeout)
        val lookingInterval = Duration.ofMillis(interval)
        try {
            return waitDriver
                    .withTimeout(lookingTimeout)
                    .pollingEvery(lookingInterval)
                    .until(ExpectedConditions.visibilityOfElementLocated(by)) as MobileElement
        } catch (e: TimeoutException) {
            if(scrollTimes <= 1) {
                throw e
            }
        }
        val windowSize = driver!!.manage().window().size
        val startX = windowSize.getWidth() / 2
        val startY = windowSize.getHeight() / 2
        return retry(scrollTimes - 1, listOf(TimeoutException::class.java)) {
            scroll(startX, startY, scrollScreen.scrollDirection, scrollScreen.scrollAmount)
            waitDriver
                    .withTimeout(lookingTimeout)
                    .pollingEvery(lookingInterval)
                    .until(ExpectedConditions.visibilityOfElementLocated(by)) as MobileElement
        }
    }

    private fun <T> retry(times: Int, exceptions: List<Type>, block: () -> T): T {
        for (i in 1..times) {
            try {
                return block()
            } catch (e: Exception) {
                if (i != times && exceptions.contains(e::class.java)) {
                    continue
                }
                throw e
            }
        }
        throw RuntimeException("Retry exceeds")
    }
}