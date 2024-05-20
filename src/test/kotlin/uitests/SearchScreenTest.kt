package uitests

import com.github.mataframework.pages.LookupConfig
import com.github.mataframework.spec.mataTest
import com.github.mataframework.spec.option.AppOptions
import org.junit.jupiter.api.Test
import skips.SkipLanding
import uitests.ui.LandingPage
import uitests.ui.SearchPage

class SearchScreenTest {

    @Test
    fun checkOpenApp() {
        val longWaitConfig = LookupConfig(30000)

        val inputText = "Форест Гамп"

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
                "Вводим '${inputText}'" {
                    waitForElement(SearchPage.editTextElement) {
                        input(it, inputText)
                    }
                    "Должен исчезнуть блок 'Рекомендации для просмотра'" {
                        waitForElementDisappear(SearchPage.recommendedToLookTextElement)
                    }
                    "Должен появится блок 'Результаты'" {
                        waitForElement(SearchPage.SearchResultSection.allResultsElement, longWaitConfig)
                    }
                    "Должен появится блок 'Предложенные элементы'" {
                        waitForElement(SearchPage.SearchResultSection.suggestsElement)
                    }
                    "Должен появится кнопка 'Все результаты'" {
                        waitForElement(SearchPage.SearchResultSection.toResultsElement)
                    }
                }
            }
        }
    }

}