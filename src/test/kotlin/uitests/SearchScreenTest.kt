package uitests

import com.github.mataframework.junit.MataTest
import com.github.mataframework.junit.MataTestSuit
import com.github.mataframework.pages.LookupConfig
import com.github.mataframework.pages.PageObject
import org.junit.jupiter.api.Test
import skips.SkipLanding
import uitests.ui.LandingPage
import uitests.ui.SearchPage

@MataTestSuit(
    appStartUpProcessors = [SkipLanding::class]
)
class SearchScreenTest {

    @MataTest
    fun checkOpenApp(pageObject: PageObject) {
        val longWaitConfig = LookupConfig(30000)

        pageObject
            .waitForElementAndClick(LandingPage.BottomNavigation.searchLocation, longWaitConfig)
            .waitForElementAndInput(SearchPage.editTextElement, "Форест Гамп")
            .waitForElementDisappear(SearchPage.recommendedToLookTextElement)
            .waitForElement(SearchPage.SearchResultSection.allResultsElement, longWaitConfig)
            .waitForElement(SearchPage.SearchResultSection.suggestsElement)
            .waitForElement(SearchPage.SearchResultSection.toResultsElement)
    }

}