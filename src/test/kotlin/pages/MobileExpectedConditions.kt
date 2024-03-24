package pages

import io.appium.java_client.MobileElement
import org.openqa.selenium.By
import org.openqa.selenium.StaleElementReferenceException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedCondition

/**
 * Canned [ExpectedCondition]s which are generally useful within webdriver tests.
 */
object MobileExpectedConditions {

    /**
     * @return the given element if it is visible and has non-zero size, otherwise null.
     */
    private fun elementIfVisible(element: MobileElement): MobileElement? {
        return if (element.isDisplayed()) element else null
    }

    /**
     * An expectation for checking that an element is present on the DOM of a page and visible.
     * Visibility means that the element is not only displayed but also has a height and width that is
     * greater than 0.
     *
     * @param locator used to find the element
     * @return the WebElement once it is located and visible
     */
    fun visibilityOfElementLocated(locator: By): ExpectedCondition<MobileElement> {
        return object : ExpectedCondition<MobileElement> {
            override fun apply(driver: WebDriver?): MobileElement? {
                return try {
                    elementIfVisible(driver!!.findElement(locator))
                } catch (e: StaleElementReferenceException) {
                    null
                }
            }

            override fun toString(): String {
                return "visibility of element located by $locator"
            }
        }
    }
}
