package uitests.ui

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.pagefactory.AndroidFindBy
import io.appium.java_client.pagefactory.AppiumFieldDecorator
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.CacheLookup
import org.openqa.selenium.support.PageFactory
import java.time.Duration

class PlusAdsPage(private val appiumFieldDecorator: AppiumFieldDecorator) {
    @CacheLookup
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"ru.kinopoisk:id/button_skip_subscription\"]")
    private lateinit var skipButtonElement: WebElement
    @CacheLookup
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"ru.kinopoisk:id/primary_offer_text\"]")
    private lateinit var primaryOfferTextElement: WebElement
    @CacheLookup
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"ru.kinopoisk:id/secondary_offer_text\"]")
    private lateinit var secondaryOfferTextElement: WebElement

    init {
        reload()
    }

    fun reload() {
        PageFactory.initElements(appiumFieldDecorator, this)
    }

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