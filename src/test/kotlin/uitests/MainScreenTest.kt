package uitests

import com.github.mataframework.junit.MataTestSuitSpecification
import com.github.mataframework.pages.PageObject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import skips.SkipLanding
import uitests.ui.LandingPage
import kotlin.test.assertTrue

@MataTestSuitSpecification(
    beforeAllProcessors = [SkipLanding::class]
)
class MainScreenTest {

    @Test
    fun checkOpenApp(pageObject: PageObject) {
        pageObject
            .waitForElement(LandingPage.TopNavigation.myCinemaLocation, 30000) {
                assertEquals("true", it.getAttribute("focused"))
            }
            .waitForElement(LandingPage.BottomNavigation.hdLocation) {
                assertTrue { it.isSelected }
                assertTrue { it.isEnabled }
            }
            .waitForElement(LandingPage.ads1stLineLocation)
            .waitForElement(LandingPage.ads2ndLineLocation)
            .waitForElement(LandingPage.tryFreeButtonLocation)
            .waitForElement(LandingPage.infoBlockLocation)
    }

}