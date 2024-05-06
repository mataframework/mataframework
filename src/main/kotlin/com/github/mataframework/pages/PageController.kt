package com.github.mataframework.pages

import com.github.mataframework.app.App
import com.github.mataframework.app.Configuration
import com.github.mataframework.exception.MataFrameworkException
import com.github.mataframework.pages.scroll.ScrollAction
import com.github.mataframework.pages.scroll.ScrollDirection
import io.appium.java_client.MobileElement
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.PointerInput
import org.openqa.selenium.interactions.Sequence
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.WebDriverWait
import java.lang.reflect.Type
import java.time.Duration

open class PageController(val app: App) {

    private val elementPollingTimeout = Configuration.getElementPollingTimeout()
    private val elementPollingInterval = Configuration.getElementPollingInterval()

    private val driver = app.driver
    private val waitDriver = WebDriverWait(driver, elementPollingTimeout, elementPollingInterval)
    private val finger = PointerInput(PointerInput.Kind.TOUCH, "finger")

    /**
     * Fit element into center of screen
     *
     * @param mobileElement element to fit
     */
    fun fitElement(mobileElement: MobileElement) {
        val windowSize = driver.manage().window().size
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

    /**
     * Scroll page from position [x], [y]
     *
     * @param x from X
     * @param y from Y
     * @param scrollAction scroll action
     */
    fun scroll(
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
            driver.perform(listOf(scroll))
        }
    }

    /**
     * Make tap on page on position [x], [y]
     *
     * @param x tap X
     * @param y tap Y
     * @param duration tap duration
     */
    fun tap(
        x: Int,
        y: Int,
        times: Int,
        duration: Long
    ) {
        val viewport = PointerInput.Origin.viewport()

        for (i in 1..times) {
            val scroll = Sequence(finger, 1)
            scroll.addAction(finger.createPointerMove(Duration.ZERO, viewport, x, y))
            scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
            scroll.addAction(finger.createPointerMove(Duration.ofMillis(duration), viewport, x, y))
            scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))
            driver.perform(listOf(scroll))
        }
    }

    /**
     * Look up for an element which matches given [condition].
     * If element is not found after timeout then throw [TimeoutException].
     * Tries to execute scrollAction until element is found or scroll action finished.
     */
    fun lookupElement(
        condition: ExpectedCondition<WebElement>,
        lookupConfig: LookupConfig
    ): MobileElement {
        val windowSize = driver.manage().window().size
        val startX = windowSize.getWidth() / 2
        val startY = windowSize.getHeight() / 2
        val (scrollAmount, scrollDirection, scrollTimes, scrollDuration) = lookupConfig.scrollAction
        val scrollItem =
            ScrollAction(scrollAmount, scrollDirection, 1, scrollDuration)
        val exceptionHandlers: Map<Type, () -> Unit> = mapOf(
            Pair(TimeoutException::class.java) {
                scroll(startX, startY, scrollItem)
            }
        )
        val lookingTimeout = Duration.ofMillis(lookupConfig.timeout)
        val lookingInterval = Duration.ofMillis(lookupConfig.interval)
        return retry(scrollTimes + 1, exceptionHandlers) {
            waitDriver
                .withTimeout(lookingTimeout)
                .pollingEvery(lookingInterval)
                .until(condition) as MobileElement
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
        throw MataFrameworkException("Retry exceeds")
    }
}