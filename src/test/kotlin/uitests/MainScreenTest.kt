package uitests

import app.App
import app.AppLauncher
import io.appium.java_client.pagefactory.AppiumFieldDecorator
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import uitests.ui.IntroductionPage
import uitests.ui.LandingPage
import uitests.ui.PlusAdsPage
import java.time.Duration
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class MainScreenTest {

    @Test
    fun checkOpenApp() {
        val landingPage = LandingPage(appiumFieldDecorator)

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