package uitests

import app.App
import app.AppLauncher
import io.appium.java_client.pagefactory.AppiumFieldDecorator
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import uitests.ui.IntroductionPage
import uitests.ui.PlusAdsPage
import java.time.Duration
import kotlin.test.assertEquals

class IntroductionScreenTest {

    @Test
    fun checkIntroductionPages() {
        val introductionPage = IntroductionPage(appiumFieldDecorator)

        assertEquals("Смотрите тысячи\nфильмов\nи сериалов", introductionPage.getDescription())

        introductionPage.clickNext()

        introductionPage.reload()

        assertEquals("Скачивайте\nв дорогу", introductionPage.getDescription())

        introductionPage.clickNext()

        val plusAdsPage = PlusAdsPage(appiumFieldDecorator)

        assertEquals("Смотрите кино\n30 дней бесплатно", plusAdsPage.getPrimaryOfferText())
        assertEquals("Подписка Плюс Больше кино", plusAdsPage.getSecondaryOfferText())

        plusAdsPage.clickSkip()
    }

    companion object {
        private lateinit var app: App
        private lateinit var appiumFieldDecorator: AppiumFieldDecorator

        @JvmStatic
        @BeforeAll
        fun setUp() {
            app = AppLauncher().launch()
            appiumFieldDecorator = AppiumFieldDecorator(app.driver, Duration.ofSeconds(30))
        }

        @JvmStatic
        @AfterAll
        fun tearDown() {
            app.close()
        }
    }
}