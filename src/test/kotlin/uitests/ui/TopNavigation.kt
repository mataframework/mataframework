package uitests.ui

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.FluentWait

class TopNavigation(driver: FluentWait<AppiumDriver<MobileElement>?>) {
    private val location = By.xpath("//androidx.compose.ui.platform.ComposeView[@resource-id=\"ru.kinopoisk:id/top_navigation\"]")
    private val myCinemaLocation = By.xpath("//android.widget.TextView[@text=\"Моё кино\"]")
    private val sportLocation = By.xpath("//android.widget.TextView[@text=\"Спорт\"]")

    private var navigationElement = driver.until(ExpectedConditions.visibilityOfElementLocated(location))
    private var myCinemaElement = navigationElement.findElement<WebElement>(myCinemaLocation)
    private var sportElement = navigationElement.findElement<WebElement>(sportLocation)

    fun isMyCinemaActive() : Boolean {
        return "true" == myCinemaElement.getAttribute("focused")
    }

    fun clickMyCinema() {
        myCinemaElement.click()
    }

    fun isSportActive() : Boolean {
        return "true" == sportElement.getAttribute("focused")
    }

    fun clickSportActive() {
        sportElement.click()
    }
}