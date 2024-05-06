package uitests.ui

import com.github.mataframework.app.Platform
import com.github.mataframework.app.PlatformProperty
import org.openqa.selenium.By


object LandingPage {

    val ads1stLineLocation: PlatformProperty<By> = PlatformProperty.of(
        Platform.ANDROID to By.xpath("//android.widget.TextView[@text=\"Подписка Плюс Больше кино\"]")
    )

    val ads2ndLineLocation: PlatformProperty<By> = PlatformProperty.of(
        Platform.ANDROID to By.xpath("//android.widget.TextView[@text=\"30 дней бесплатно\"]")
    )

    val tryFreeButtonLocation: PlatformProperty<By> = PlatformProperty.of(
        Platform.ANDROID to By.xpath("//android.widget.TextView[@text=\"Попробовать бесплатно\"]")
    )

    val infoBlockLocation: PlatformProperty<By> = PlatformProperty.of(
        Platform.ANDROID to By.xpath(
            "//android.widget.TextView[@text=\"Сервис «Кинопоиск» может " +
                    "содержать информацию, не предназначенную для несовершеннолетних. \n" +
                    "Федеральные каналы доступны для бесплатного просмотра круглосуточно.\"]"
        )
    )

    object TopNavigation {

        val navigationLocation: PlatformProperty<By> = PlatformProperty.of(
            Platform.ANDROID to By.xpath(
                "//androidx.compose.ui.platform.ComposeView[@resource-id=\"ru.kinopoisk:id/top_navigation\"]"
            )
        )

        val myCinemaLocation: PlatformProperty<By> = PlatformProperty.of(
            Platform.ANDROID to By.xpath(
                "//android.widget.TextView[@text=\"Моё кино\"]"
            )
        )

        val sportLocation: PlatformProperty<By> = PlatformProperty.of(
            Platform.ANDROID to By.xpath(
                "//android.widget.TextView[@text=\"Спорт\"]"
            )
        )

    }

    object BottomNavigation {

        val hdLocation: PlatformProperty<By> = PlatformProperty.of(
            Platform.ANDROID to By.id(
                "ru.kinopoisk:id/hd"
            )
        )

        val mediaLocation: PlatformProperty<By> = PlatformProperty.of(
            Platform.ANDROID to By.id(
                "ru.kinopoisk:id/afisha"
            )
        )

        val searchLocation: PlatformProperty<By> = PlatformProperty.of(
            Platform.ANDROID to By.id(
                "ru.kinopoisk:id/search"
            )
        )

    }

}