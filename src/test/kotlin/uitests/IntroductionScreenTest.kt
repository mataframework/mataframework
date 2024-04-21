package uitests

import com.github.mataframework.junit.MataInject
import com.github.mataframework.junit.MataTestSuit
import com.github.mataframework.pages.PageObject
import org.junit.jupiter.api.Test
import uitests.ui.IntroductionPage
import uitests.ui.PlusAdsPage
import kotlin.test.assertEquals

@MataTestSuit
class IntroductionScreenTest {
    @MataInject
    private lateinit var pageObject: PageObject

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
}