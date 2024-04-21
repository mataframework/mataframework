package uitests

import com.github.mataframework.junit.MataTestSuit
import com.github.mataframework.pages.PageObject
import com.github.mataframework.pages.scroll.ScrollAction
import com.github.mataframework.pages.scroll.ScrollDirection
import org.junit.jupiter.api.Test
import skips.SkipLanding
import uitests.ui.LandingPage
import uitests.ui.MediaPage

@MataTestSuit(
    beforeEachStartUpProcessors = [SkipLanding::class]
)
class MediaScreenTest {
    @Test
    fun checkOpenApp(pageObject: PageObject) {
        pageObject
            .waitForElementAndClick(LandingPage.BottomNavigation.mediaLocation, 30000)
            .waitForElementAndScroll(
                MediaPage.soonFilmsCarouselElement,
                ScrollAction(100),
                timeout = 1000,
                scrollAction = ScrollAction(100, scrollTimes = 10),
                fitRequired = true
            )
            .waitForElementAndScroll(
                MediaPage.soonFilmsCarouselElement,
                ScrollAction(100, ScrollDirection.RIGHT)
            )
            .waitForElement(
                MediaPage.trailersCarouselElement,
                timeout = 1000,
                scrollAction = ScrollAction(100, scrollTimes = 10),
                fitRequired = true
            )
            .waitForElementAndScroll(
                MediaPage.trailersCarouselElement,
                ScrollAction(100, ScrollDirection.RIGHT)
            )
    }

}