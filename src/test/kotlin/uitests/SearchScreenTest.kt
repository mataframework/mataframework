package uitests

import com.github.mataframework.junit.MataTestSuitSpecification
import com.github.mataframework.pages.PageObject
import org.junit.jupiter.api.Test
import skips.SkipLanding
import uitests.ui.LandingPage
import uitests.ui.SearchPage

@MataTestSuitSpecification(
    beforeAllProcessors = [SkipLanding::class]
)
class SearchScreenTest {

    @Test
    fun checkOpenApp(pageObject: PageObject) {
        pageObject
            .waitForElementAndClick(LandingPage.BottomNavigation.searchLocation, 30000)
            .waitForElementAndInput(SearchPage.editTextElement, "Форест Гамп")
            .waitForElementDisappear(SearchPage.recommendedToLookTextElement)
            .waitForElement(SearchPage.SearchResultSection.allResultsElement, 30000)
            .waitForElement(SearchPage.SearchResultSection.suggestsElement)
            .waitForElement(SearchPage.SearchResultSection.toResultsElement)
    }

}