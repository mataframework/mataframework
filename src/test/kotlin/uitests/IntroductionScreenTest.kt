package uitests

import app.App
import app.AppLauncher
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import pages.PageObject
import uitests.ui.IntroductionPage
import uitests.ui.PlusAdsPage
import kotlin.test.assertEquals

class IntroductionScreenTest {

    @Test
    fun checkIntroductionPages() {
        pageObject
            .waitForElementAndGetText(IntroductionPage.descriptionLocation, 30000) {
                assertEquals("Смотрите тысячи\nфильмов\nи сериалов", it)
            }
            .waitForElementAndClick(IntroductionPage.nextButtonLocation)
            .waitForElementAndGetText(IntroductionPage.descriptionLocation, 30000) {
                assertEquals("Скачивайте\nв дорогу", it)
            }
            .waitForElementAndClick(IntroductionPage.nextButtonLocation)
            .waitForElementAndGetText(PlusAdsPage.primaryOfferTextLocation, 30000) {
                assertEquals("Смотрите кино\n30 дней бесплатно", it)
            }
            .waitForElementAndGetText(PlusAdsPage.secondaryOfferTextLocation) {
                assertEquals("Подписка Плюс Больше кино", it)
            }
            .waitForElementAndClick(PlusAdsPage.skipButtonLocation)
    }

    companion object {
        private lateinit var app: App
        private lateinit var pageObject: PageObject

        @JvmStatic
        @BeforeAll
        fun setUp() {
            app = AppLauncher().launch()
            pageObject = PageObject(app)
        }

        @JvmStatic
        @AfterAll
        fun tearDown() {
            app.close()
        }
    }
}