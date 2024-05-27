package uitests

import com.github.mataframework.pages.LookupConfig
import com.github.mataframework.spec.mataTest
import com.github.mataframework.spec.option.AppOptions
import io.qameta.allure.Allure
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import uitests.ui.IntroductionPage
import uitests.ui.PlusAdsPage
import kotlin.test.assertEquals

class IntroductionScreenTest {
    @Test
    @DisplayName("Проверка вступительных экранов")
    fun checkIntroductionPages() {
        val longWaitConfig = LookupConfig(30000)

        mataTest(
            appOptions = AppOptions(cleanRun = true)
        ) {
            "Рекламный экран" {
                record {
                    "Проверяем наличие вступительного текста" {
                        waitForElement(IntroductionPage.descriptionLocation, longWaitConfig) {
                            Allure.attachment("Вступительный текст", it.text)
                            assertEquals("Смотрите тысячи\nфильмов\nи сериалов", it.text)
                        }
                    }
                    "Нажимаем на кнопку 'Далее'" {
                        waitForElement(IntroductionPage.nextButtonLocation) {
                            click(it)
                        }
                    }
                }
            }
            "Второй рекламный экран" {
                record {
                    "Описание должно обновиться" {
                        waitForElement(IntroductionPage.descriptionLocation, longWaitConfig) {
                            assertEquals("Скачивайте\nв дорогу", it.text)
                        }
                    }
                    "Нажимаем на кнопку 'Далее'" {
                        waitForElement(IntroductionPage.nextButtonLocation) {
                            click(it)
                        }
                    }
                }
            }
            "Экран 'Предложение Подписки Плюс'" {
                record("Экран 'Предложение Подписки Плюс'") {
                    "Ожидается текст с предложением пробного периода" {
                        waitForElement(PlusAdsPage.primaryOfferTextLocation, longWaitConfig) {
                            assertEquals("Смотрите кино\n30 дней бесплатно", it.text)
                        }
                    }
                    "Ожидается текст о большем количестве кино" {
                        waitForElement(PlusAdsPage.secondaryOfferTextLocation, longWaitConfig) {
                            assertEquals("Подписка Плюс Больше кино", it.text)
                        }
                    }
                    "Нажимаем на кнопку 'Пропустить'" {
                        waitForElement(PlusAdsPage.skipButtonLocation) {
                            click(it)
                        }
                    }
                }
            }
        }
    }
}