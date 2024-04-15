package uitests

import app.App
import app.AppLauncher
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import pages.PageObject
import pages.scroll.ScrollAction
import pages.scroll.ScrollDirection
import uitests.ui.IntroductionPage
import uitests.ui.LandingPage
import uitests.ui.MediaPage
import uitests.ui.PlusAdsPage

class MediaScreenTest {

    @Test
    fun checkOpenApp() {
        pageObject
                .waitForElementAndClick(LandingPage.BottomNavigation.mediaLocation, 30000)
                .waitForElementAndScroll(MediaPage.soonFilmsCarouselElement, ScrollAction(100))
                .waitForElementAndScroll(
                        MediaPage.soonFilmsCarouselElement,
                        ScrollAction(100, ScrollDirection.RIGHT)
                )
                .waitForElement(
                        MediaPage.trailersCarouselElement,
                        scrollAction = ScrollAction(100, scrollTimes = 10),
                        fitRequired = true
                )
                .waitForElementAndScroll(
                        MediaPage.trailersCarouselElement,
                        ScrollAction(100, ScrollDirection.RIGHT)
                )
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