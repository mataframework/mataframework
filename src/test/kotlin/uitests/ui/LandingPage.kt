package uitests.ui

import app.App
import io.appium.java_client.MobileElement
import org.openqa.selenium.By
import pages.PageObject


class LandingPage(app: App) : PageObject(app) {

    private val contentLocation: By = choose(
            By.xpath("(//android.widget.FrameLayout[@resource-id=\"ru.kinopoisk:id/fragment_container\"])[2]"),
            By.xpath("") //TODO: fill for iOS
    )

    private val ads1stLineLocation: By = choose(
            By.xpath("//android.widget.TextView[@text=\"Подписка Плюс Больше кино\"]"),
            By.xpath("") //TODO: fill for iOS
    )

    private val ads2ndLineLocation: By = choose(
            By.xpath("//android.widget.TextView[@text=\"30 дней бесплатно\"]"),
            By.xpath("") //TODO: fill for iOS
    )

    private val tryFreeButtonLocation: By = choose(
            By.xpath("//android.widget.TextView[@text=\"Попробовать бесплатно\"]"),
            By.xpath("") //TODO: fill for iOS
    )

    private val infoBlockLocation: By = choose(
            By.xpath("//android.widget.TextView[@text=\"Сервис «Кинопоиск» может " +
                    "содержать информацию, не предназначенную для несовершеннолетних. \n" +
                    "Федеральные каналы доступны для бесплатного просмотра круглосуточно.\"]"),
            By.xpath("") //TODO: fill for iOS
    )

    var contentElement: MobileElement = waitForElement(contentLocation)
    var ads1stLineElement: MobileElement = contentElement.findElement(ads1stLineLocation)
    var ads2ndLineElement: MobileElement = contentElement.findElement(ads2ndLineLocation)
    var tryFreeButton: MobileElement = contentElement.findElement(tryFreeButtonLocation)
    var infoBlockElement: MobileElement = contentElement.findElement(infoBlockLocation)

    val topNavigation = TopNavigation(app)
    val bottomNavigation = BottomNavigation(app)

}