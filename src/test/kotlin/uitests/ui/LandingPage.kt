package uitests.ui

import io.appium.java_client.pagefactory.AndroidFindBy
import io.appium.java_client.pagefactory.AppiumFieldDecorator
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.CacheLookup
import org.openqa.selenium.support.PageFactory


class LandingPage(appiumFieldDecorator: AppiumFieldDecorator) {

    val topNavigation = TopNavigation(appiumFieldDecorator)
    @CacheLookup
    @AndroidFindBy(xpath = "(//android.widget.FrameLayout[@resource-id=\"ru.kinopoisk:id/fragment_container\"])[2]")
    lateinit var contentElement: WebElement
    val bottomNavigation = BottomNavigation(appiumFieldDecorator)

    init {
        PageFactory.initElements(appiumFieldDecorator, this)
    }
}