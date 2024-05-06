package uitests.ui

import com.github.mataframework.app.Platform
import com.github.mataframework.app.PlatformProperty
import org.openqa.selenium.By

object MediaPage {

    val soonFilmsCarouselElement: PlatformProperty<By> = PlatformProperty(
        Platform.ANDROID to By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"ru.kinopoisk:id/soon_films_carousel_rv\"]")
    )

    val trailersCarouselElement: PlatformProperty<By> = PlatformProperty(
        Platform.ANDROID to By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"ru.kinopoisk:id/trailers_carousel_rv\"]")
    )
}