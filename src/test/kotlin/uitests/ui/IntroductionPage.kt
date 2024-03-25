package uitests.ui

import app.App
import io.appium.java_client.MobileElement
import org.openqa.selenium.By
import pages.PageObject

class IntroductionPage(app: App) : PageObject(app) {

    private val descriptionLocation: By = choose(
            By.id("ru.kinopoisk:id/description"),
            By.xpath("") //TODO: fill for iOS
    )

    private val nextButtonLocation: By = choose(
            By.id("ru.kinopoisk:id/button_next"),
            By.xpath("") //TODO: fill for iOS
    )

    private lateinit var descriptionElement: MobileElement

    init {
        reload()
    }

    fun reload() {
        descriptionElement = waitForElement(descriptionLocation, 30000)
    }

    fun clickNext() {
        waitForElementAndClick(nextButtonLocation)
    }

    fun getDescription() : String {
        return descriptionElement.text
    }

}