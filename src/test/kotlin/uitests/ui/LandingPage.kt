package uitests.ui

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.FluentWait

class LandingPage(driver: FluentWait<AppiumDriver<MobileElement>?>) {
    private val contentLocation = By.xpath("(//android.widget.FrameLayout[@resource-id=\"ru.kinopoisk:id/fragment_container\"])[2]")

    val topNavigation = TopNavigation(driver)
    val contentElement = driver.until(ExpectedConditions.visibilityOfElementLocated(contentLocation))
    val bottomNavigation = BottomNavigation(driver)

}