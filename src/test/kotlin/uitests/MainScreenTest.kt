package uitests

import app.App
import app.AppLauncher
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import uitests.ui.IntroductionPage
import uitests.ui.LandingPage
import uitests.ui.PlusAdsPage
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class MainScreenTest {

    @Test
    fun checkOpenApp() {
        val landingPage = LandingPage(app)

        assertTrue { landingPage.topNavigation.isMyCinemaActive() }
        assertTrue { landingPage.bottomNavigation.isHDActive() }

        assertNotNull(landingPage.ads1stLineElement)
        assertTrue { landingPage.ads1stLineElement.isDisplayed }

        assertNotNull(landingPage.ads2ndLineElement)
        assertTrue { landingPage.ads2ndLineElement.isDisplayed }

        assertNotNull(landingPage.tryFreeButton)
        assertTrue { landingPage.tryFreeButton.isDisplayed }

        assertNotNull(landingPage.infoBlockElement)
        assertTrue { landingPage.infoBlockElement.isDisplayed }
    }

    companion object {
        private lateinit var app: App

        @JvmStatic
        @BeforeAll
        fun setUp() {
            app = AppLauncher().launch()

            skipIntroductionPages()
        }

        private fun skipIntroductionPages() {
            val introductionPage = IntroductionPage(app)
            introductionPage.clickNext()

            introductionPage.reload()
            introductionPage.clickNext()

            val plusAdsPage = PlusAdsPage(app)
            plusAdsPage.clickSkip()
        }

        @JvmStatic
        @AfterAll
        fun tearDown() {
            app.close()
        }
    }
}