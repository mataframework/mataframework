package uitests

import app.App
import app.AppLauncher
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import uitests.ui.IntroductionPage
import uitests.ui.PlusAdsPage
import kotlin.test.assertEquals

class IntroductionScreenTest {

    @Test
    fun checkIntroductionPages() {
        val introductionPage = IntroductionPage(app)

        assertEquals("Смотрите тысячи\nфильмов\nи сериалов", introductionPage.getDescription())

        introductionPage.clickNext()

        introductionPage.reload()

        assertEquals("Скачивайте\nв дорогу", introductionPage.getDescription())

        introductionPage.clickNext()

        val plusAdsPage = PlusAdsPage(app)

        assertEquals("Смотрите кино\n30 дней бесплатно", plusAdsPage.getPrimaryOfferText())
        assertEquals("Подписка Плюс Больше кино", plusAdsPage.getSecondaryOfferText())

        plusAdsPage.clickSkip()
    }

    companion object {
        private lateinit var app: App

        @JvmStatic
        @BeforeAll
        fun setUp() {
            app = AppLauncher().launch()
        }

        @JvmStatic
        @AfterAll
        fun tearDown() {
            app.close()
        }
    }
}