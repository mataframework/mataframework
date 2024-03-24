package uitests.ui

import io.appium.java_client.pagefactory.AndroidFindBy
import io.appium.java_client.pagefactory.AppiumFieldDecorator
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.CacheLookup
import org.openqa.selenium.support.PageFactory

class BottomNavigation(appiumFieldDecorator: AppiumFieldDecorator) {
    @CacheLookup
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"ru.kinopoisk:id/bottom_navigation_bar\"]")
    private lateinit var navigationElement: WebElement

    @CacheLookup
    @AndroidFindBy(id = "ru.kinopoisk:id/hd")
    private lateinit var hdElement: WebElement

    @CacheLookup
    @AndroidFindBy(id = "ru.kinopoisk:id/afisha")
    private lateinit var mediaElement: WebElement

    init {
        PageFactory.initElements(appiumFieldDecorator, this)
    }

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