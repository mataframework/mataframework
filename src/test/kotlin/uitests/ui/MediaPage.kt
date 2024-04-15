package uitests.ui

import app.PlatformProperty
import org.openqa.selenium.By

object MediaPage {

    val soonFilmsCarouselElement: PlatformProperty<By> = PlatformProperty(
            By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"ru.kinopoisk:id/soon_films_carousel_rv\"]"),
            By.xpath("") //TODO: fill for iOS
    )


    val trailersCarouselElement: PlatformProperty<By> = PlatformProperty(
            By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"ru.kinopoisk:id/trailers_carousel_rv\"]"),
            By.xpath("") //TODO: fill for iOS
    )

}