package uitests

import app.App
import app.AppLauncher
import io.appium.java_client.pagefactory.AppiumFieldDecorator
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.openqa.selenium.support.ui.WebDriverWait
import uitests.ui.IntroductionPage
import uitests.ui.LandingPage
import uitests.ui.PlusAdsPage
import java.time.Duration
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class MainScreenTest {

    @Test
    fun checkOpenApp() {
        val waitDriver = WebDriverWait(app.driver, 30, 150)

        val landingPage = LandingPage(appiumFieldDecorator, app, waitDriver)

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
        private lateinit var appiumFieldDecorator: AppiumFieldDecorator

        @JvmStatic
        @BeforeAll
        fun setUp() {
            app = AppLauncher().launch()
            appiumFieldDecorator = AppiumFieldDecorator(app.driver, Duration.ofSeconds(30))

            skipIntroductionPages()
        }

        private fun skipIntroductionPages() {
            val introductionPage = IntroductionPage(appiumFieldDecorator)
            introductionPage.clickNext()

            introductionPage.reload()
            introductionPage.clickNext()

            val plusAdsPage = PlusAdsPage(appiumFieldDecorator)
            plusAdsPage.clickSkip()
        }

        @JvmStatic
        @AfterAll
        fun tearDown() {
            app.close()
        }
    }
}