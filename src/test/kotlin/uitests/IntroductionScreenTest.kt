package uitests

import com.github.mataframework.junit.MataInject
import com.github.mataframework.junit.MataTest
import com.github.mataframework.junit.MataTestSuit
import com.github.mataframework.pages.LookupConfig
import com.github.mataframework.pages.PageObject
import org.junit.jupiter.api.DisplayName
import uitests.ui.IntroductionPage
import uitests.ui.PlusAdsPage
import kotlin.test.assertEquals

@MataTestSuit
class IntroductionScreenTest {
    @MataInject
    private lateinit var pageObject: PageObject

    @MataTest(recordExecution = true)
    @DisplayName("Проверка вступительного экрана")
    fun checkIntroductionPages() {
        val longWaitConfig = LookupConfig(30000)

        pageObject
            .waitForElementAndGetText(IntroductionPage.descriptionLocation, longWaitConfig) {
                assertEquals("Смотрите тысячи\nфильмов\nи сериалов1", it)
            }
            .waitForElementAndClick(IntroductionPage.nextButtonLocation)
            .waitForElementAndGetText(IntroductionPage.descriptionLocation, longWaitConfig) {
                assertEquals("Скачивайте\nв дорогу", it)
            }
            .waitForElementAndClick(IntroductionPage.nextButtonLocation)
            .waitForElementAndGetText(PlusAdsPage.primaryOfferTextLocation, longWaitConfig) {
                assertEquals("Смотрите кино\n30 дней бесплатно", it)
            }
            .waitForElementAndGetText(PlusAdsPage.secondaryOfferTextLocation) {
                assertEquals("Подписка Плюс Больше кино", it)
            }
            .waitForElementAndClick(PlusAdsPage.skipButtonLocation)
    }
}