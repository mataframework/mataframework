package uitests

import app.App
import app.AppLauncher
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import pages.PageObject
import pages.scroll.ScrollDirection
import pages.scroll.ScrollScreen
import uitests.ui.IntroductionPage
import uitests.ui.LandingPage
import uitests.ui.MediaPage
import uitests.ui.PlusAdsPage

class MediaScreenTest {

    @Test
    fun checkOpenApp() {
        pageObject
                .waitForElementAndClick(LandingPage.BottomNavigation.mediaLocation, 30000)
                .waitForElementAndScroll(MediaPage.soonFilmsCarouselElement, ScrollDirection.DOWN, 100)
                .waitForElementAndScroll(MediaPage.soonFilmsCarouselElement, ScrollDirection.RIGHT, 100)
                .waitForElement(
                        MediaPage.trailersCarouselElement,
                        scrollScreen = ScrollScreen(ScrollDirection.DOWN, 200, 10),
                        fitRequired = true
                )
                .waitForElementAndScroll(MediaPage.soonFilmsCarouselElement, ScrollDirection.RIGHT, 100)
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