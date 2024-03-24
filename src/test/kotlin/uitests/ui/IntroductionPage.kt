package uitests.ui

import io.appium.java_client.pagefactory.AndroidFindBy
import io.appium.java_client.pagefactory.AppiumFieldDecorator
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.PageFactory

class IntroductionPage(private val appiumFieldDecorator: AppiumFieldDecorator) {
    @AndroidFindBy(id = "ru.kinopoisk:id/description")
    private lateinit var descriptionElement: WebElement
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