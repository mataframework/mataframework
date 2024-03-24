package uitests.ui

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.FluentWait

class PlusAdsPage(driver: FluentWait<AppiumDriver<MobileElement>?>) {
    private val skipButtonLocation = By.xpath("//android.widget.Button[@resource-id=\"ru.kinopoisk:id/button_skip_subscription\"]")
    private val primaryOfferTextLocation = By.xpath("//android.widget.TextView[@resource-id=\"ru.kinopoisk:id/primary_offer_text\"]")
    private val secondaryOfferTextLocation = By.xpath("//android.widget.TextView[@resource-id=\"ru.kinopoisk:id/secondary_offer_text\"]")

    private val skipButtonElement: WebElement = driver.until(
            ExpectedConditions.visibilityOfElementLocated(skipButtonLocation)
    )
    private val primaryOfferTextElement: WebElement = driver.until(
            ExpectedConditions.visibilityOfElementLocated(primaryOfferTextLocation)
    )
    private val secondaryOfferTextElement: WebElement = driver.until(
            ExpectedConditions.visibilityOfElementLocated(secondaryOfferTextLocation)
    )

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