package uitests.ui

import com.github.mataframework.app.Platform
import com.github.mataframework.app.PlatformProperty
import org.openqa.selenium.By

object PlusAdsPage {
    val skipButtonLocation: PlatformProperty<By> = PlatformProperty(
        Platform.ANDROID to By.xpath("//android.widget.Button[@resource-id=\"ru.kinopoisk:id/button_skip_subscription\"]")
    )

    val skipVPNButtonLocation: PlatformProperty<By> = PlatformProperty(
        Platform.ANDROID to By.id("ru.kinopoisk:id/button_payment")
    )

    val primaryOfferTextLocation: PlatformProperty<By> = PlatformProperty(
        Platform.ANDROID to By.xpath("//android.widget.TextView[@resource-id=\"ru.kinopoisk:id/primary_offer_text\"]")
    )

    val secondaryOfferTextLocation: PlatformProperty<By> = PlatformProperty(
        Platform.ANDROID to By.xpath("//android.widget.TextView[@resource-id=\"ru.kinopoisk:id/secondary_offer_text\"]")
    )
}