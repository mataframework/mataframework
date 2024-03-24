package uitests.ui

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.pagefactory.AndroidFindBy
import io.appium.java_client.pagefactory.AppiumFieldDecorator
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.PageFactory
import java.time.Duration

class IntroductionPage(private val appiumFieldDecorator: AppiumFieldDecorator) {
//    @CacheLookup
//    @AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id=\"ru.kinopoisk:id/container\"]")
//    private lateinit var contentContainerElement: WebElement
//    @CacheLookup
    @AndroidFindBy(id = "ru.kinopoisk:id/description")
    private lateinit var descriptionElement: WebElement
//    @CacheLookup
    @AndroidFindBy(id = "ru.kinopoisk:id/button_next")
    private lateinit var nextButtonElement: WebElement

    init {
        reload()
    }

    fun reload() {
        PageFactory.initElements(appiumFieldDecorator, this)
    }

    fun clickNext() {
        nextButtonElement.click()
    }

    fun getDescription() : String {
        return descriptionElement.text
    }

}