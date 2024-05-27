package uitests.ui

import com.github.mataframework.app.Platform
import com.github.mataframework.app.PlatformProperty
import org.openqa.selenium.By

object SearchPage {

    val editTextElement: PlatformProperty<By> = PlatformProperty(
        Platform.ANDROID to By.id("ru.kinopoisk:id/search_edit_text")
    )

    val resetButton: PlatformProperty<By> = PlatformProperty(
        Platform.ANDROID to By.id("ru.kinopoisk:id/reset_button_container")
    )

    val recommendedToLookTextElement: PlatformProperty<By> = PlatformProperty(
        Platform.ANDROID to By.id("ru.kinopoisk:id/search_recycler_view")
    )

    object SearchResultSection {

        val allResultsElement: PlatformProperty<By> = PlatformProperty(
            Platform.ANDROID to By.id("ru.kinopoisk:id/all")
        )

        val suggestsElement: PlatformProperty<By> = PlatformProperty(
            Platform.ANDROID to By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"ru.kinopoisk:id/suggests_recycler_view\"]")
        )

        val toResultsElement: PlatformProperty<By> = PlatformProperty(
            Platform.ANDROID to By.xpath("//android.widget.Button[@resource-id=\"ru.kinopoisk:id/search_all_button\"]")
        )

    }
}