package uitests.ui

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.FluentWait

class BottomNavigation(driver: FluentWait<AppiumDriver<MobileElement>?>) {
    private val location = By.xpath("//android.widget.FrameLayout[@resource-id=\"ru.kinopoisk:id/bottom_navigation_bar\"]")
    private val hdLocation = By.id("ru.kinopoisk:id/hd")
    private val mediaLocation = By.id("ru.kinopoisk:id/afisha")

    private var navigationElement = driver.until(ExpectedConditions.visibilityOfElementLocated(location))
    private var hdElement = navigationElement.findElement<WebElement>(hdLocation)
    private var mediaElement = navigationElement.findElement<WebElement>(mediaLocation)

    fun isHDActive() : Boolean {
        return "true" == hdElement.getAttribute("selected")
    }

    fun clickHD() {
        hdElement.click()
    }

    fun isMediaActive() : Boolean {
        return "true" == mediaElement.getAttribute("selected")
    }

    fun clickMediaActive() {
        mediaElement.click()
    }
}