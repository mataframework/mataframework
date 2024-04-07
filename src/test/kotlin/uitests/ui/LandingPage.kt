package uitests.ui

import app.PlatformProperty
import org.openqa.selenium.By


object LandingPage {

    val contentLocation: PlatformProperty<By> = PlatformProperty(
        By.xpath("(//android.widget.FrameLayout[@resource-id=\"ru.kinopoisk:id/fragment_container\"])[2]"),
        By.xpath("") //TODO: fill for iOS
    )

    val ads1stLineLocation: PlatformProperty<By> = PlatformProperty(
        By.xpath("//android.widget.TextView[@text=\"Подписка Плюс Больше кино\"]"),
        By.xpath("") //TODO: fill for iOS
    )

    val ads2ndLineLocation: PlatformProperty<By> = PlatformProperty(
        By.xpath("//android.widget.TextView[@text=\"30 дней бесплатно\"]"),
        By.xpath("") //TODO: fill for iOS
    )

    val tryFreeButtonLocation: PlatformProperty<By> = PlatformProperty(
        By.xpath("//android.widget.TextView[@text=\"Попробовать бесплатно\"]"),
        By.xpath("") //TODO: fill for iOS
    )

    val infoBlockLocation: PlatformProperty<By> = PlatformProperty(
        By.xpath(
            "//android.widget.TextView[@text=\"Сервис «Кинопоиск» может " +
                    "содержать информацию, не предназначенную для несовершеннолетних. \n" +
                    "Федеральные каналы доступны для бесплатного просмотра круглосуточно.\"]"
        ),
        By.xpath("") //TODO: fill for iOS
    )

    object TopNavigation {

        val navigationLocation: PlatformProperty<By> = PlatformProperty(
            By.xpath("//androidx.compose.ui.platform.ComposeView[@resource-id=\"ru.kinopoisk:id/top_navigation\"]"),
            By.xpath("") //TODO: fill for iOS
        )

        val myCinemaLocation: PlatformProperty<By> = PlatformProperty(
            By.xpath("//android.widget.TextView[@text=\"Моё кино\"]"),
            By.xpath("") //TODO: fill for iOS
        )

        val sportLocation: PlatformProperty<By> = PlatformProperty(
            By.xpath("//android.widget.TextView[@text=\"Спорт\"]"),
            By.xpath("") //TODO: fill for iOS
        )
    }

    object BottomNavigation {
        val navigationLocation: PlatformProperty<By> = PlatformProperty(
            By.xpath("//android.widget.FrameLayout[@resource-id=\"ru.kinopoisk:id/bottom_navigation_bar\"]"),
            By.xpath("") //TODO: fill for iOS
        )

        val hdLocation: PlatformProperty<By> = PlatformProperty(
            By.id("ru.kinopoisk:id/hd"),
            By.xpath("") //TODO: fill for iOS
        )

        val mediaLocation: PlatformProperty<By> = PlatformProperty(
            By.id("ru.kinopoisk:id/afisha"),
            By.xpath("") //TODO: fill for iOS
        )

        val searchLocation: PlatformProperty<By> = PlatformProperty(
            By.id("ru.kinopoisk:id/search"),
            By.xpath("") //TODO: fill for iOS
        )
    }

}