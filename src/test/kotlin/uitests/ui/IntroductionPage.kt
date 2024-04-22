package uitests.ui

import com.github.mataframework.app.Platform
import com.github.mataframework.app.PlatformProperty
import org.openqa.selenium.By

object IntroductionPage {

    val descriptionLocation: PlatformProperty<By> = PlatformProperty.of(
        Platform.ANDROID to By.id("ru.kinopoisk:id/description")
    )

    val nextButtonLocation: PlatformProperty<By> = PlatformProperty.of(
        Platform.ANDROID to By.id("ru.kinopoisk:id/button_next")
    )

}