package uitests

import com.github.mataframework.app.App
import com.github.mataframework.app.AppLauncher
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import com.github.mataframework.pages.PageObject
import uitests.ui.IntroductionPage
import uitests.ui.LandingPage
import uitests.ui.PlusAdsPage
import kotlin.test.assertTrue

class MainScreenTest {

    @Test
    fun checkOpenApp() {
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