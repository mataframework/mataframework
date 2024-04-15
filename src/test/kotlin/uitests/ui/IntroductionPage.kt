package uitests.ui

import com.github.mataframework.app.PlatformProperty
import org.openqa.selenium.By

object IntroductionPage {

    val descriptionLocation: PlatformProperty<By> = PlatformProperty(
        By.id("ru.kinopoisk:id/description"),
        By.xpath("") //TODO: fill for iOS
    )

    val nextButtonLocation: PlatformProperty<By> = PlatformProperty(
        By.id("ru.kinopoisk:id/button_next"),
        By.xpath("") //TODO: fill for iOS
    )

}