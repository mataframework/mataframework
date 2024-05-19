package uitests

import com.github.mataframework.junit.MataTest
import com.github.mataframework.junit.MataTestSuit
import com.github.mataframework.junit.spec.MataTestCase
import com.github.mataframework.pages.LookupConfig
import io.qameta.allure.Allure
import org.junit.jupiter.api.DisplayName
import uitests.ui.IntroductionPage
import uitests.ui.PlusAdsPage
import kotlin.test.assertEquals

@MataTestSuit
class IntroductionScreenTest : MataTestCase() {
    @MataTest(recordExecution = true)
    @DisplayName("Проверка вступительных экранов")
    fun checkIntroductionPages() {
        val longWaitConfig = LookupConfig(30000)

        mataTest {
            "Рекламный экран" {
                "Проверяем наличие вступительного текста" {
                    waitForElementAndGetText(IntroductionPage.descriptionLocation, longWaitConfig) {
                        Allure.attachment("Вступительный текст", it)
                        assertEquals("Смотрите тысячи\nфильмов\nи сериалов", it)
                    }
                }
                "Нажимаем на кнопку 'Далее'" {
                    waitForElementAndClick(IntroductionPage.nextButtonLocation)
                }
            }
            "Второй рекламный экран" {
                "Описание должно обновиться" {
                    waitForElementAndGetText(IntroductionPage.descriptionLocation, longWaitConfig) {
                        assertEquals("Скачивайте\nв дорогу", it)
                    }
                }
                "Нажимаем на кнопку 'Далее'" {
                    waitForElementAndClick(IntroductionPage.nextButtonLocation)
                }
            }
            "Экран 'Предложение Подписки Плюс'" {
                "Ожидается текст с предложением пробного периода" {
                    waitForElementAndGetText(PlusAdsPage.primaryOfferTextLocation, longWaitConfig) {
                        assertEquals("Смотрите кино\n30 дней бесплатно", it)
                    }
                }
                "Ожидается текст о большем количестве кино" {
                    waitForElementAndGetText(PlusAdsPage.secondaryOfferTextLocation, longWaitConfig) {
                        assertEquals("Подписка Плюс Больше кино", it)
                    }
                }
                "Нажимаем на кнопку 'Пропустить'" {
                    waitForElementAndClick(PlusAdsPage.skipButtonLocation)
                }
            }
        }
    }
}