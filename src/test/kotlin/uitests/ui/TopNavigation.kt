package uitests.ui

import io.appium.java_client.pagefactory.AndroidFindBy
import io.appium.java_client.pagefactory.AppiumFieldDecorator
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.CacheLookup
import org.openqa.selenium.support.PageFactory

class TopNavigation(appiumFieldDecorator: AppiumFieldDecorator) {
    @CacheLookup
    @AndroidFindBy(xpath = "//androidx.compose.ui.platform.ComposeView[@resource-id=\"ru.kinopoisk:id/top_navigation\"]")
    private lateinit var navigationElement: WebElement

    @CacheLookup
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Моё кино\"]")
    private lateinit var myCinemaElement: WebElement

    @CacheLookup
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Спорт\"]")
    private lateinit var sportElement: WebElement

    init {
        PageFactory.initElements(appiumFieldDecorator, this)
    }

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