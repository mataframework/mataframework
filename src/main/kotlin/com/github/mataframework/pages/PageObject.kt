package com.github.mataframework.pages

import com.github.mataframework.app.App
import com.github.mataframework.app.Configuration
import com.github.mataframework.app.PlatformProperty
import com.github.mataframework.pages.scroll.ScrollAction
import com.github.mataframework.pages.scroll.ScrollDirection
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import org.openqa.selenium.By
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.interactions.PointerInput
import org.openqa.selenium.interactions.Sequence
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.lang.reflect.Type
import java.time.Duration
import java.util.concurrent.TimeUnit

open class PageObject(val app: App) {

    private val elementPollingTimeout = Configuration.getElementPollingTimeout()
    private val elementPollingInterval = Configuration.getElementPollingInterval()
    private val noop = { _: MobileElement -> }
    private val noScroll = ScrollAction(0, scrollTimes = 0)

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
     * @param scrollAction - scrolling policy for element searching
     * @param fitRequired - if true, element will be fit to center of screen
     * @param consumer - element handler
     *
     * @exception org.openqa.selenium.TimeoutException if element not found for [timeout]
     * @return self-reference
     */
    fun waitForElement(
        byPlatformProperty: PlatformProperty<By>,
        timeout: Long = elementPollingTimeout,
        interval: Long = elementPollingInterval,
        scrollAction: ScrollAction = noScroll,
        fitRequired: Boolean = false,
        consumer: (MobileElement) -> Unit = noop
    ): PageObject {
        val by = byPlatformProperty.getValue()
        val mobileElement = lookupElement(by, timeout, interval, scrollAction)
        if (fitRequired) {
            fitElement(mobileElement)
        }
        consumer(mobileElement)
        return this
    }

    /**
     * Looking for an element with locator [byPlatformProperty] until it disappears.
     *
     * @param byPlatformProperty - element locator
     * @param attempts - amount of attempts to element looking
     * @param sleepBetweenAttempts - sleep milliseconds between attempts
     * @param timeout - max awaiting timeout
     * @param interval - checking interval
     *
     * @exception org.openqa.selenium.TimeoutException if element not found for [timeout]
     * @return self-reference
     */
    fun waitForElementDisappear(
        byPlatformProperty: PlatformProperty<By>,
        attempts: Int = 1,
        sleepBetweenAttempts: Long = 500,
        timeout: Long = elementPollingTimeout,
        interval: Long = elementPollingInterval
    ): PageObject {
        val by = byPlatformProperty.getValue()
        for (i in 1..attempts) {
            try {
                lookupElement(by, timeout, interval, noScroll)
            } catch (e: TimeoutException) {
                if (sleepBetweenAttempts > 0) {
                    TimeUnit.MILLISECONDS.sleep(sleepBetweenAttempts)
                }
                return this
            }
        }
        throw RuntimeException("Element '$by' is not disappeared")
    }

    /**
     * Looking for an element with locator [byPlatformProperty].
     * In case if element found invoke [org.openqa.selenium.remote.RemoteWebElement.click].
     *
     * @param byPlatformProperty - element locator
     * @param timeout - max awaiting timeout
     * @param interval - checking interval
     * @param scrollAction - scrolling policy for element searching
     * @param fitRequired - if true, element will be fit to center of screen
     *
     * @exception org.openqa.selenium.TimeoutException if element not found for [timeout]
     * @return self-reference
     */
    fun waitForElementAndClick(
        byPlatformProperty: PlatformProperty<By>,
        timeout: Long = elementPollingTimeout,
        interval: Long = elementPollingInterval,
        scrollAction: ScrollAction = noScroll,
        fitRequired: Boolean = false
    ): PageObject {
        return waitForElement(byPlatformProperty, timeout, interval, scrollAction, fitRequired) {
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
     * @param scrollAction - scrolling policy for element searching
     * @param fitRequired - if true, element will be fit to center of screen
     *
     * @exception org.openqa.selenium.TimeoutException if element not found for [timeout]
     * @return self-reference
     */
    fun waitForElementAndGetText(
        byPlatformProperty: PlatformProperty<By>,
        timeout: Long = elementPollingTimeout,
        interval: Long = elementPollingInterval,
        scrollAction: ScrollAction = noScroll,
        fitRequired: Boolean = false,
        consumer: (String) -> Unit
    ): PageObject {
        return waitForElement(byPlatformProperty, timeout, interval, scrollAction, fitRequired) {
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
     * @param scrollAction - scrolling policy for element searching
     * @param fitRequired - if true, element will be fit to center of screen
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
        scrollAction: ScrollAction = noScroll,
        fitRequired: Boolean = false,
        consumer: (String) -> Unit
    ): PageObject {
        val attributeKey = attributePlatformProperty.getValue()
        return waitForElement(byPlatformProperty, timeout, interval, scrollAction, fitRequired) {
            consumer(it.getAttribute(attributeKey))
        }
    }

    /**
     * Looking for an element with locator [byPlatformProperty].
     * In case if element found:
     * 1. and [click] passed true invoke [org.openqa.selenium.remote.RemoteWebElement.click].
     * 2. invoke [io.appium.java_client.MobileElement.setValue] and pass [input].
     *
     * @param byPlatformProperty - element locator
     * @param input - data to input
     * @param click - true to click, false to not click
     * @param timeout - max awaiting timeout
     * @param interval - checking interval
     * @param scrollAction - scrolling policy for element searching
     * @param fitRequired - if true, element will be fit to center of screen
     *
     * @exception org.openqa.selenium.TimeoutException if element not found for [timeout]
     * @return self-reference
     */
    fun waitForElementAndInput(
        byPlatformProperty: PlatformProperty<By>,
        input: String,
        click: Boolean = true,
        timeout: Long = elementPollingTimeout,
        interval: Long = elementPollingInterval,
        scrollAction: ScrollAction = noScroll,
        fitRequired: Boolean = false
    ): PageObject {
        return waitForElement(byPlatformProperty, timeout, interval, scrollAction, fitRequired) {
            if (click) {
                it.click()
            }
            it.sendKeys(input)
        }
    }

    /**
     * Looking for an element with locator [byPlatformProperty].
     * In case if element found invoke scroll using [elementScrollAction] policy.
     *
     * @param byPlatformProperty - element locator
     * @param elementScrollAction - element scrolling policy
     * @param timeout - max awaiting timeout
     * @param interval - checking interval
     * @param scrollAction - scrolling policy for element searching
     * @param fitRequired - if true, element will be fit to center of screen
     *
     * @exception org.openqa.selenium.TimeoutException if element not found for [timeout]
     * @return self-reference
     */
    fun waitForElementAndScroll(
        byPlatformProperty: PlatformProperty<By>,
        elementScrollAction: ScrollAction,
        timeout: Long = elementPollingTimeout,
        interval: Long = elementPollingInterval,
        scrollAction: ScrollAction = noScroll,
        fitRequired: Boolean = false
    ): PageObject {
        return waitForElement(byPlatformProperty, timeout, interval, scrollAction, fitRequired) {
            val location = it.location
            val x = location.x
            val y = location.y

            scroll(x, y, elementScrollAction)
        }
    }

    private fun scroll(
        x: Int,
        y: Int,
        scrollAction: ScrollAction
    ) {
        val viewport = PointerInput.Origin.viewport()
        val scrollAmount = scrollAction.scrollAmount
        val timeout = scrollAction.scrollDuration

        for (i in 1..scrollAction.scrollTimes) {
            val scroll = Sequence(finger, 0)
            scroll.addAction(finger.createPointerMove(Duration.ZERO, viewport, x, y))
            scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
            when (scrollAction.scrollDirection) {
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
    }

    private fun lookupElement(
        by: By,
        timeout: Long,
        interval: Long,
        scrollAction: ScrollAction
    ): MobileElement {
        val windowSize = driver!!.manage().window().size
        val startX = windowSize.getWidth() / 2
        val startY = windowSize.getHeight() / 2
        val scrollItem =
            ScrollAction(scrollAction.scrollAmount, scrollAction.scrollDirection, 1, scrollAction.scrollDuration)
        val exceptionHandlers: Map<Type, () -> Unit> = mapOf(
            Pair(TimeoutException::class.java) {
                scroll(startX, startY, scrollItem)
            }
        )
        val scrollTimes = scrollAction.scrollTimes
        val lookingTimeout = Duration.ofMillis(timeout)
        val lookingInterval = Duration.ofMillis(interval)
        return retry(scrollTimes + 1, exceptionHandlers) {
            waitDriver
                .withTimeout(lookingTimeout)
                .pollingEvery(lookingInterval)
                .until(ExpectedConditions.visibilityOfElementLocated(by)) as MobileElement
        }
    }

    private fun <T> retry(times: Int, exceptionHandlers: Map<Type, () -> Unit>, block: () -> T): T {
        for (i in 1..times) {
            try {
                return block()
            } catch (e: Exception) {
                val exceptionHandler = exceptionHandlers[e::class.java]
                if (i != times && exceptionHandler != null) {
                    exceptionHandler()
                    continue
                }
                throw e
            }
        }
        throw RuntimeException("Retry exceeds")
    }

    private fun fitElement(mobileElement: MobileElement) {
        val windowSize = driver!!.manage().window().size
        val windowHeight = windowSize.height
        val startX = windowSize.width / 2
        val startY = windowHeight / 2

        val elementLocation = mobileElement.location.y
        val exceptedElementLocation = startY - mobileElement.size.height / 2

        val scrollAction = if (exceptedElementLocation > elementLocation) {
            ScrollAction(exceptedElementLocation - elementLocation, ScrollDirection.UP)
        } else {
            ScrollAction(elementLocation - exceptedElementLocation)
        }
        scroll(startX, startY, scrollAction)
    }
}