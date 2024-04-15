package uitests.ui

import app.PlatformProperty
import org.openqa.selenium.By

object SearchPage {

    val editTextElement: PlatformProperty<By> = PlatformProperty(
            By.id("ru.kinopoisk:id/search_edit_text"),
            By.xpath("") //TODO: fill for iOS
    )

    val recommendedToLookTextElement: PlatformProperty<By> = PlatformProperty(
            By.id("ru.kinopoisk:id/search_recycler_view"),
            By.xpath("") //TODO: fill for iOS
    )

    object SearchResultSection {

        val allResultsElement: PlatformProperty<By> = PlatformProperty(
                By.id("ru.kinopoisk:id/all"),
                By.id("") //TODO: fill for iOS
        )

        val suggestsElement: PlatformProperty<By> = PlatformProperty(
                By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"ru.kinopoisk:id/suggests_recycler_view\"]"),
                By.id("") //TODO: fill for iOS
        )

        val toResultsElement: PlatformProperty<By> = PlatformProperty(
                By.xpath("//android.widget.Button[@resource-id=\"ru.kinopoisk:id/search_all_button\"]"),
                By.id("") //TODO: fill for iOS
        )

    }
}