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
import uitests.ui.UIAttributes
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MainScreenTest {

    @Test
    fun checkOpenApp() {
        pageObject
            .waitForElementAndGetAttribute(LandingPage.TopNavigation.myCinemaLocation, UIAttributes.focused, 30000) {
                assertEquals("true", it)
            }
            .waitForElementAndGetAttribute(LandingPage.BottomNavigation.hdLocation, UIAttributes.selected) {
                assertEquals("true", it)
            }
            .waitForElement(LandingPage.ads1stLineLocation) {
                assertTrue { it.isDisplayed }
            }
            .waitForElement(LandingPage.ads2ndLineLocation) {
                assertTrue { it.isDisplayed }
            }
            .waitForElement(LandingPage.tryFreeButtonLocation) {
                assertTrue { it.isDisplayed }
            }
            .waitForElement(LandingPage.infoBlockLocation) {
                assertTrue { it.isDisplayed }
            }
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