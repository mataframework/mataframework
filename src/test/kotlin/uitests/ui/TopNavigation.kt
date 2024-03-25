package uitests.ui

import app.App
import io.appium.java_client.MobileElement
import org.openqa.selenium.By
import pages.PageObject

class TopNavigation(app: App) : PageObject(app) {

    private val navigationLocation: By = by(
            By.xpath("//androidx.compose.ui.platform.ComposeView[@resource-id=\"ru.kinopoisk:id/top_navigation\"]"),
            By.xpath("") //TODO: fill for iOS
    )

    private val myCinemaLocation: By = by(
            By.xpath("//android.widget.TextView[@text=\"Моё кино\"]"),
            By.xpath("") //TODO: fill for iOS
    )

    private val sportLocation: By = by(
            By.xpath("//android.widget.TextView[@text=\"Спорт\"]"),
            By.xpath("") //TODO: fill for iOS
    )


    private var navigationElement: MobileElement = waitForElement(navigationLocation)
    private var myCinemaElement: MobileElement = navigationElement.findElement(myCinemaLocation)
    private var sportElement: MobileElement = navigationElement.findElement(sportLocation)

    fun isMyCinemaActive(): Boolean {
        return "true" == myCinemaElement.getAttribute("focused")
    }

    fun clickMyCinema() {
        myCinemaElement.click()
    }

    fun isSportActive(): Boolean {
        return "true" == sportElement.getAttribute("focused")
    }

    fun clickSportActive() {
        sportElement.click()
    }
}