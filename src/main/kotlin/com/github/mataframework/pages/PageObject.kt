package com.github.mataframework.pages

import com.github.mataframework.app.App
import com.github.mataframework.app.Platform
import com.github.mataframework.app.PlatformProperty
import com.github.mataframework.exception.MataFrameworkException
import com.github.mataframework.pages.scroll.ScrollAction
import io.appium.java_client.MobileElement
import org.openqa.selenium.By
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.remote.RemoteWebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import java.util.concurrent.TimeUnit

open class PageObject(val app: App<*>) {

    private val noop = { _: MobileElement -> }
    private val defaultConfig = LookupConfig()

    private val pageController = PageController(app)


    /**
     * Looking for an element with locator [byPlatformProperty].
     *
     * @param byPlatformProperty - element locator
     * @param lookupConfig - element lookup configuration
     * @param consumer - element handler
     *
     * @exception org.openqa.selenium.TimeoutException if element not found for required timeout
     * @return self-reference
     */
    fun waitForElement(
        byPlatformProperty: PlatformProperty<By>,
        lookupConfig: LookupConfig = defaultConfig,
        consumer: (MobileElement) -> Unit = noop
    ): PageObject {
        val by = byPlatformProperty.get()
        val condition = ExpectedConditions.visibilityOfElementLocated(by)
        val mobileElement = pageController.lookupElement(condition, lookupConfig)
        if (lookupConfig.fitRequired) {
            pageController.fitElement(mobileElement)
        }
        consumer(mobileElement)
        return this
    }

    /**
     * Looking for an element with locator [byPlatformProperty].
     * In case if element found invoke [org.openqa.selenium.remote.RemoteWebElement.click].
     * If element not found when do nothing.
     *
     * @param byPlatformProperty - element locator
     * @param lookupConfig - element lookup configuration
     * @param consumer - element handler
     *
     * @return self-reference
     */
    fun waitForElementOrNull(
        byPlatformProperty: PlatformProperty<By>,
        lookupConfig: LookupConfig = defaultConfig,
        consumer: (MobileElement) -> Unit = noop
    ): PageObject {
        try {
            return waitForElement(byPlatformProperty, lookupConfig, consumer)
        } catch (_: TimeoutException) {
        }
        return this
    }

    /**
     * Looking for an element with locator [byPlatformProperty].
     *
     * @param byPlatformProperty - element locator
     * @param lookupConfig - element lookup configuration
     * @param consumer - element handler
     *
     * @exception org.openqa.selenium.TimeoutException if element not found for required timeout
     * @return self-reference
     */
    fun waitForOverlappedElement(
        byPlatformProperty: PlatformProperty<By>,
        lookupConfig: LookupConfig = defaultConfig,
        consumer: (MobileElement) -> Unit = noop
    ): PageObject {
        val by = byPlatformProperty.get()
        val condition = ExpectedConditions.presenceOfElementLocated(by)
        val mobileElement = pageController.lookupElement(condition, lookupConfig)
        if (lookupConfig.fitRequired) {
            pageController.fitElement(mobileElement)
        }
        consumer(mobileElement)
        return this
    }

    /**
     * Looking for an element with locator [byPlatformProperty].
     * In case if element found invoke [org.openqa.selenium.remote.RemoteWebElement.click].
     * If element not found when do nothing.
     *
     * @param byPlatformProperty - element locator
     * @param lookupConfig - element lookup configuration
     * @param consumer - element handler
     *
     * @return self-reference
     */
    fun waitForOverlappedElementOrNull(
        byPlatformProperty: PlatformProperty<By>,
        lookupConfig: LookupConfig = defaultConfig,
        consumer: (MobileElement) -> Unit = noop
    ): PageObject {
        try {
            return waitForOverlappedElement(byPlatformProperty, lookupConfig, consumer)
        } catch (_: TimeoutException) {
        }
        return this
    }

    /**
     * Looking for an element with locator [byPlatformProperty] until it disappears.
     *
     * @param byPlatformProperty - element locator
     * @param lookupConfig - element lookup configuration
     * @param attempts - amount of attempts to element looking
     * @param sleepBetweenAttempts - sleep milliseconds between attempts
     *
     * @exception org.openqa.selenium.TimeoutException if element not found for required timeout
     * @return self-reference
     */
    fun waitForElementDisappear(
        byPlatformProperty: PlatformProperty<By>,
        lookupConfig: LookupConfig = defaultConfig,
        attempts: Int = 1,
        sleepBetweenAttempts: Long = 500
    ): PageObject {
        val by = byPlatformProperty.get()
        val condition = ExpectedConditions.visibilityOfElementLocated(by)
        val retryLookupConfig = LookupConfig(lookupConfig.timeout, lookupConfig.interval)
        for (i in 1..attempts) {
            try {
                pageController.lookupElement(condition, retryLookupConfig)
            } catch (e: TimeoutException) {
                if (sleepBetweenAttempts > 0) {
                    TimeUnit.MILLISECONDS.sleep(sleepBetweenAttempts)
                }
                return this
            }
        }
        throw MataFrameworkException("Element '$by' is not disappeared")
    }

    /**
     * Looking for an element with locator [byPlatformProperty].
     * In case if element found invoke [org.openqa.selenium.remote.RemoteWebElement.click].
     *
     * @param byPlatformProperty - element locator
     * @param lookupConfig - element lookup configuration
     *
     * @exception org.openqa.selenium.TimeoutException if element not found for required timeout
     * @return self-reference
     */
    fun waitForElementAndClick(
        byPlatformProperty: PlatformProperty<By>,
        lookupConfig: LookupConfig = defaultConfig
    ): PageObject {
        return waitForElement(byPlatformProperty, lookupConfig) {
            it.click()
        }
    }

    /**
     * Tap in passed position
     *
     * @return self-reference
     */
    fun tap(
        x: Int,
        y: Int,
        times: Int = 1,
        durationProperty: PlatformProperty<Long> = PlatformProperty(
            Platform.ANDROID to 50,
            Platform.IOS to 50
        )
    ): PageObject {
        val duration = durationProperty.get()
        pageController.tap(x, y, times, duration)
        return this
    }

    /**
     * Tap in passed position
     *
     * @return self-reference
     */
    fun longTap(
        x: Int,
        y: Int,
        times: Int = 1,
        durationProperty: PlatformProperty<Long> = PlatformProperty(
            Platform.ANDROID to 750,
            Platform.IOS to 750
        )
    ): PageObject {
        val duration = durationProperty.get()
        pageController.tap(x, y, times, duration)
        return this
    }

    /**
     * Looking for an element with locator [byPlatformProperty].
     * In case if element found invoke [org.openqa.selenium.remote.RemoteWebElement.getText] will be returned.
     *
     * @param byPlatformProperty - element locator
     * @param lookupConfig - element lookup configuration
     * @param consumer - text handler
     *
     * @exception org.openqa.selenium.TimeoutException if element not found for required timeout
     * @return self-reference
     */
    fun waitForElementAndGetText(
        byPlatformProperty: PlatformProperty<By>,
        lookupConfig: LookupConfig = defaultConfig,
        consumer: (String) -> Unit
    ): PageObject {
        return waitForElement(byPlatformProperty, lookupConfig) {
            consumer(it.text)
        }
    }

    /**
     * Looking for an element with locator [byPlatformProperty].
     * In case if element found invoke [org.openqa.selenium.remote.RemoteWebElement.getAttribute] will be returned.
     *
     * @param byPlatformProperty - element locator
     * @param attributePlatformProperty - attribute key value
     * @param lookupConfig - element lookup configuration
     * @param consumer - text handler
     *
     * @exception org.openqa.selenium.TimeoutException if element not found for required timeout
     * @return self-reference
     */
    fun waitForElementAndGetAttribute(
        byPlatformProperty: PlatformProperty<By>,
        attributePlatformProperty: PlatformProperty<String>,
        lookupConfig: LookupConfig = defaultConfig,
        consumer: (String) -> Unit
    ): PageObject {
        val attributeKey = attributePlatformProperty.get()
        return waitForElement(byPlatformProperty, lookupConfig) {
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
     * @param lookupConfig - element lookup configuration
     * @param click - true to click, false to not click
     *
     * @exception org.openqa.selenium.TimeoutException if element not found for required timeout
     * @return self-reference
     */
    fun waitForElementAndInput(
        byPlatformProperty: PlatformProperty<By>,
        input: String,
        lookupConfig: LookupConfig = defaultConfig,
        click: Boolean = true
    ): PageObject {
        return waitForElement(byPlatformProperty, lookupConfig) {
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
     * @param lookupConfig - element lookup configuration
     *
     * @exception org.openqa.selenium.TimeoutException if element not found for required timeout
     * @return self-reference
     */
    fun waitForElementAndScroll(
        byPlatformProperty: PlatformProperty<By>,
        elementScrollAction: ScrollAction,
        lookupConfig: LookupConfig = defaultConfig,
    ): PageObject {
        return waitForElement(byPlatformProperty, lookupConfig) {
            val location = it.location
            val x = location.x
            val y = location.y

            pageController.scroll(x, y, elementScrollAction)
        }
    }

    fun scroll(
        element: RemoteWebElement,
        elementScrollAction: ScrollAction
    ) {
        val location = element.location
        val x = location.x
        val y = location.y

        pageController.scroll(x, y, elementScrollAction)
    }

    fun click(
        element: RemoteWebElement
    ) {
        element.click()
    }

    fun input(
        element: RemoteWebElement,
        input: String,
        click: Boolean = true
    ) {
        if (click) {
            element.click()
        }
        element.sendKeys(input)
    }
}