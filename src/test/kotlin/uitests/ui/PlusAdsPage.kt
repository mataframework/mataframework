package uitests.ui

import app.App
import io.appium.java_client.MobileElement
import org.openqa.selenium.By
import pages.PageObject

class PlusAdsPage(app: App) : PageObject(app) {
    private val skipButtonLocation: By = choose(
            By.xpath("//android.widget.Button[@resource-id=\"ru.kinopoisk:id/button_skip_subscription\"]"),
            By.xpath("") //TODO: fill for iOS
    )
    private val primaryOfferTextLocation: By = choose(
            By.xpath("//android.widget.TextView[@resource-id=\"ru.kinopoisk:id/primary_offer_text\"]"),
            By.xpath("") //TODO: fill for iOS
    )

    private val secondaryOfferTextLocation: By = choose(
            By.xpath("//android.widget.TextView[@resource-id=\"ru.kinopoisk:id/secondary_offer_text\"]"),
            By.xpath("") //TODO: fill for iOS
    )

    private var skipButtonElement: MobileElement = waitForElement(skipButtonLocation)
    private var primaryOfferTextElement: MobileElement = waitForElement(primaryOfferTextLocation)
    private var secondaryOfferTextElement: MobileElement = waitForElement(secondaryOfferTextLocation)

    fun clickSkip() {
        skipButtonElement.click()
    }

    fun getPrimaryOfferText(): String {
        return primaryOfferTextElement.text
    }

    fun getSecondaryOfferText(): String {
        return secondaryOfferTextElement.text
    }
}