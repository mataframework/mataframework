package uitests.ui

import com.github.mataframework.app.PlatformProperty
import org.openqa.selenium.By

object PlusAdsPage {
    val skipButtonLocation: PlatformProperty<By> = PlatformProperty(
        By.xpath("//android.widget.Button[@resource-id=\"ru.kinopoisk:id/button_skip_subscription\"]"),
        By.xpath("") //TODO: fill for iOS
    )

    val primaryOfferTextLocation: PlatformProperty<By> = PlatformProperty(
        By.xpath("//android.widget.TextView[@resource-id=\"ru.kinopoisk:id/primary_offer_text\"]"),
        By.xpath("") //TODO: fill for iOS
    )

    val secondaryOfferTextLocation: PlatformProperty<By> = PlatformProperty(
        By.xpath("//android.widget.TextView[@resource-id=\"ru.kinopoisk:id/secondary_offer_text\"]"),
        By.xpath("") //TODO: fill for iOS
    )
}