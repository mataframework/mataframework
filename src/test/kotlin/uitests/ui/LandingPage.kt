package uitests.ui

import app.App
import io.appium.java_client.pagefactory.AppiumFieldDecorator
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import pages.PageObject


class LandingPage(appiumFieldDecorator: AppiumFieldDecorator, app: App, waitDriver: WebDriverWait) : PageObject(app) {

    val topNavigation = TopNavigation(appiumFieldDecorator)
    val bottomNavigation = BottomNavigation(appiumFieldDecorator)

    private val contentLocation: By = by(
            By.xpath("(//android.widget.FrameLayout[@resource-id=\"ru.kinopoisk:id/fragment_container\"])[2]"),
            By.xpath("") //TODO: fill for iOS
    )

    private val ads1stLineLocation: By = by(
            By.xpath("//android.widget.TextView[@text=\"Подписка Плюс Больше кино\"]"),
            By.xpath("") //TODO: fill for iOS
    )

    private val ads2ndLineLocation: By = by(
            By.xpath("//android.widget.TextView[@text=\"30 дней бесплатно\"]"),
            By.xpath("") //TODO: fill for iOS
    )

    private val tryFreeButtonLocation: By = by(
            By.xpath("//android.widget.TextView[@text=\"Попробовать бесплатно\"]"),
            By.xpath("") //TODO: fill for iOS
    )

    private val infoBlockLocation: By = by(
            By.xpath("//android.widget.TextView[@text=\"Сервис «Кинопоиск» может " +
                    "содержать информацию, не предназначенную для несовершеннолетних. \n" +
                    "Федеральные каналы доступны для бесплатного просмотра круглосуточно.\"]"),
            By.xpath("") //TODO: fill for iOS
    )

    var contentElement: WebElement
    var ads1stLineElement: WebElement
    var ads2ndLineElement: WebElement
    var tryFreeButton: WebElement
    var infoBlockElement: WebElement

    init {
        contentElement = waitDriver.until(ExpectedConditions.visibilityOfElementLocated(contentLocation))
        ads1stLineElement = contentElement.findElement(ads1stLineLocation)
        ads2ndLineElement = contentElement.findElement(ads2ndLineLocation)
        tryFreeButton = contentElement.findElement(tryFreeButtonLocation)
        infoBlockElement = contentElement.findElement(infoBlockLocation)
    }
}