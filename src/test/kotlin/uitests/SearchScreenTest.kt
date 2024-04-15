package uitests

import app.App
import app.AppLauncher
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import pages.PageObject
import uitests.ui.IntroductionPage
import uitests.ui.LandingPage
import uitests.ui.PlusAdsPage
import uitests.ui.SearchPage

class SearchScreenTest {

    @Test
    fun checkOpenApp() {
        pageObject
            .waitForElementAndClick(LandingPage.BottomNavigation.searchLocation, 30000)
            .waitForElementAndInput(SearchPage.editTextElement, "Форест Гамп")
            .waitForElementDisappear(SearchPage.recommendedToLookTextElement)
            .waitForElement(SearchPage.SearchResultSection.allResultsElement, 30000)
            .waitForElement(SearchPage.SearchResultSection.suggestsElement)
            .waitForElement(SearchPage.SearchResultSection.toResultsElement)
    }

    companion object {
        private lateinit var app: App
        private lateinit var pageObject: PageObject

        @JvmStatic
        @BeforeAll
        fun setUp() {
            app = AppLauncher().launch()
            pageObject = PageObject(app)

            skipIntroductionPages()
        }

        private fun skipIntroductionPages() {
            pageObject
                .waitForElementAndClick(IntroductionPage.nextButtonLocation)
                .waitForElementAndClick(IntroductionPage.nextButtonLocation)
                .waitForElementAndClick(PlusAdsPage.skipButtonLocation)
        }

        @JvmStatic
        @AfterAll
        fun tearDown() {
            app.close()
        }
    }
}