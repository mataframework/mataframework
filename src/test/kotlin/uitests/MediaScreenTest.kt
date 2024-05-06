package uitests

import com.github.mataframework.junit.MataTestSuit
import com.github.mataframework.pages.LookupConfig
import com.github.mataframework.pages.PageObject
import com.github.mataframework.pages.scroll.ScrollAction
import com.github.mataframework.pages.scroll.ScrollDirection
import org.junit.jupiter.api.Test
import skips.SkipLanding
import uitests.ui.LandingPage
import uitests.ui.MediaPage

@MataTestSuit(
    appStartUpProcessors = [SkipLanding::class]
)
class MediaScreenTest {
    @Test
    fun checkOpenApp(pageObject: PageObject) {
        val longWaitConfig = LookupConfig(30000)
        val longWaitAndScrollConfig = LookupConfig(
            1000,
            scrollAction = ScrollAction(100, scrollTimes = 10),
            fitRequired = true
        )

        pageObject
            .waitForElementAndClick(LandingPage.BottomNavigation.mediaLocation, longWaitConfig)
            .waitForElementAndScroll(
                MediaPage.soonFilmsCarouselElement,
                ScrollAction(100),
                longWaitAndScrollConfig
            )
            .waitForElementAndScroll(
                MediaPage.soonFilmsCarouselElement,
                ScrollAction(100, ScrollDirection.RIGHT)
            )
            .waitForElement(MediaPage.trailersCarouselElement, longWaitAndScrollConfig)
            .waitForElementAndScroll(
                MediaPage.trailersCarouselElement,
                ScrollAction(100, ScrollDirection.RIGHT)
            )
    }

}