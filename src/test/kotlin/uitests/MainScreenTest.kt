package uitests

import app.App
import app.AppLauncher
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.support.ui.FluentWait
import uitests.ui.IntroductionPage
import uitests.ui.LandingPage
import uitests.ui.PlusAdsPage
import java.time.Duration
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class MainScreenTest {

    @Test
    fun checkOpenApp() {
        val landingPage = LandingPage(wait)

        assertTrue { landingPage.topNavigation.isMyCinemaActive() }
        assertTrue { landingPage.bottomNavigation.isHDActive() }

        val contentElement = landingPage.contentElement
        assertNotNull(
                contentElement.findElement(
                        By.xpath("//android.widget.TextView[@text=\"Подписка Плюс Больше кино\"]")
                )
        )
        assertNotNull(
                contentElement.findElement(
                        By.xpath("//android.widget.TextView[@text=\"30 дней бесплатно\"]")
                )
        )
        assertNotNull(
                contentElement.findElement(
                        By.xpath("//android.widget.TextView[@text=\"Попробовать бесплатно\"]")
                )
        )
        assertNotNull(
                contentElement.findElement(
                        By.xpath("//android.widget.TextView[@text=\"Сервис «Кинопоиск» может " +
                                "содержать информацию, не предназначенную для несовершеннолетних. \n" +
                                "Федеральные каналы доступны для бесплатного просмотра круглосуточно.\"]")
                )
        )
    }

    companion object {
        private lateinit var app: App
        private lateinit var wait: FluentWait<AppiumDriver<MobileElement>?>

        @JvmStatic
        @BeforeAll
        fun setUp() {
            app = AppLauncher().launch()
            wait = FluentWait(app.driver)
                    .withTimeout(Duration.ofSeconds(15))
                    .pollingEvery(Duration.ofMillis(250))
                    .ignoring(NoSuchElementException::class.java)

            skipIntroductionPages()
        }

        private fun skipIntroductionPages() {
            var introductionPage = IntroductionPage(wait)
            introductionPage.clickNext()

            introductionPage = IntroductionPage(wait)
            introductionPage.clickNext()

            val plusAdsPage = PlusAdsPage(wait)
            plusAdsPage.clickSkip()
        }

        @JvmStatic
        @AfterAll
        fun tearDown() {
            app.close()
        }
    }
}