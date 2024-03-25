package uitests.ui

import app.App
import io.appium.java_client.MobileElement
import org.openqa.selenium.By
import pages.PageObject

class BottomNavigation(app: App) : PageObject(app) {
    private val navigationLocation: By = choose(
            By.xpath("//android.widget.FrameLayout[@resource-id=\"ru.kinopoisk:id/bottom_navigation_bar\"]"),
            By.xpath("") //TODO: fill for iOS
    )

    private val hdLocation: By = choose(
            By.id( "ru.kinopoisk:id/hd"),
            By.xpath("") //TODO: fill for iOS
    )

    private val mediaLocation: By = choose(
            By.id("ru.kinopoisk:id/afisha"),
            By.xpath("") //TODO: fill for iOS
    )


    private var navigationElement: MobileElement = waitForElement(navigationLocation)
    private var hdElement: MobileElement = navigationElement.findElement(hdLocation)
    private var mediaElement: MobileElement = navigationElement.findElement(mediaLocation)

    fun isHDActive(): Boolean {
        return "true" == hdElement.getAttribute("selected")
    }

    fun clickHD() {
        hdElement.click()
    }

    fun isMediaActive(): Boolean {
        return "true" == mediaElement.getAttribute("selected")
    }

    fun clickMediaActive() {
        mediaElement.click()
    }
}