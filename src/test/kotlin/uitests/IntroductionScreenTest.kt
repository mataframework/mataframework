package uitests

import app.App
import app.AppLauncher
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.support.ui.FluentWait
import uitests.ui.IntroductionPage
import uitests.ui.PlusAdsPage
import java.time.Duration
import kotlin.test.assertEquals

class IntroductionScreenTest {

    @Test
    fun checkIntroductionPages() {
        var introductionPage = IntroductionPage(wait)

        assertEquals("Смотрите тысячи\nфильмов\nи сериалов", introductionPage.getDescription())

        introductionPage.clickNext()

        introductionPage = IntroductionPage(wait)

        assertEquals("Скачивайте\nв дорогу", introductionPage.getDescription())

        introductionPage.clickNext()

        val plusAdsPage = PlusAdsPage(wait)

        assertEquals("Смотрите кино\n30 дней бесплатно", plusAdsPage.getPrimaryOfferText())
        assertEquals("Подписка Плюс Больше кино", plusAdsPage.getSecondaryOfferText())

        plusAdsPage.clickSkip()
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
        }

        @JvmStatic
        @AfterAll
        fun tearDown() {
            app.close()
        }
    }
}