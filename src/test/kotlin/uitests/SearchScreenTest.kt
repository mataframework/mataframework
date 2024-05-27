package uitests

import com.github.mataframework.pages.LookupConfig
import com.github.mataframework.spec.mataTest
import com.github.mataframework.spec.option.AppOptions
import org.junit.jupiter.api.Test
import skips.SkipLanding
import uitests.ui.LandingPage
import uitests.ui.SearchPage
import kotlin.test.assertEquals

class SearchScreenTest {

    @Test
    fun checkOpenApp() {
        val longWaitConfig = LookupConfig(30000)

        val inputList = listOf("Форест Гамп", "Титаник")

        mataTest(
            appOptions = AppOptions(cleanRun = true),
            appStartUpListeners = arrayOf(SkipLanding::class)
        ) {
            "Переходим на страницу 'Поиск'" {
                waitForElement(LandingPage.BottomNavigation.searchLocation, longWaitConfig) {
                    click(it)
                }
            }
            "Страница 'Поиск'" {
                inputList.forEachIndexed { index, inputText ->
                    if(index > 0) {
                        "${step()} Сбрасываем значение поиска" {
                            waitForElementOrNull(SearchPage.resetButton) {
                                click(it)
                            }
                        }
                    }
                    "${step()} Вводим '${inputText}'" {
                        waitForElement(SearchPage.editTextElement) {
                            input(it, inputText)
                            hideKeyboard()
                        }
                        "${stepTrace(".", 1)} Должен исчезнуть блок 'Рекомендации для просмотра'" {
                            waitForElementDisappear(SearchPage.recommendedToLookTextElement)
                        }
                        "${stepTrace(".", 1)} Должен появится блок 'Результаты'" {
                            waitForElement(SearchPage.SearchResultSection.allResultsElement, longWaitConfig) {
                                screenshot()
                            }
                        }
                        "${stepTrace(".", 1)} Должен появится блок 'Предложенные элементы'" {
                            waitForElement(SearchPage.SearchResultSection.suggestsElement)
                        }
                        "${stepTrace(".", 1)} Может появится кнопка 'К результатам'" {
                            waitForElementOrNull(SearchPage.SearchResultSection.toResultsElement) {
                                "Кнопка 'К результатам' появилась" {
                                    assertEquals("true", it.getAttribute("clickable"))
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
