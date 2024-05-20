package uitests

import com.github.mataframework.pages.LookupConfig
import com.github.mataframework.spec.mataTest
import com.github.mataframework.spec.option.AppOptions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import skips.SkipLanding
import uitests.ui.LandingPage
import kotlin.test.assertTrue

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class MainScreenTest  {

    @Test
    @Order(1)
    fun `Тестирование главной страницы`() {
        val longWaitConfig = LookupConfig(30000)

        mataTest(
            appOptions = AppOptions(cleanRun = true),
            appStartUpListeners = arrayOf(SkipLanding::class)
        ) {
            "Шаг {step} Главная страница" {
                screenshot("Главная страница")
                "Шаг {step} В верхнем меню активна кнопка 'Моё кино'" {
                    waitForElement(LandingPage.TopNavigation.myCinemaLocation, longWaitConfig) {
                        screenshot(it, "Элемент верхнего меню")
                        assertEquals("true", it.getAttribute("focused"))
                    }
                }
                "Шаг {step} В нижнем меню активна кнопка 'Главная'" {
                    waitForElement(LandingPage.BottomNavigation.hdLocation) {
                        assertTrue { it.isSelected }
                        assertTrue { it.isEnabled }
                    }
                }
                "Шаг {step} На странице присутствует 1-ый рекламный текст" {
                    waitForElement(LandingPage.ads1stLineLocation)
                }
                "Шаг {step} На странице присутствует 2-ой рекламный текст" {
                    waitForElement(LandingPage.ads2ndLineLocation)
                }
                "Шаг {step} На странице присутствует кнопка 'Попробовать бесплатно'" {
                    waitForElement(LandingPage.tryFreeButtonLocation)
                }
                "Шаг {step} На странице присутствует информационный блок" {
                    waitForElement(LandingPage.infoBlockLocation)
                }
            }
        }
    }

    @Test
    @Order(2)
    fun checkDoSomething() {
        mataTest(
            appOptions = AppOptions(cleanRun = false)
        ) {
            "Главная страница" {
                record("Главная страница") {
                    "В нижнем меню тап по кнопке 'Главная'" {
                        waitForElement(LandingPage.BottomNavigation.hdLocation) {
                            tap(it.location.x, it.location.y)
                        }
                    }
                    "На странице присутствует 1-ый рекламный текст" {
                        waitForElement(LandingPage.ads1stLineLocation)
                    }
                    "В нижнем меню долгий тап по кнопке 'Главная'" {
                        waitForElement(LandingPage.BottomNavigation.hdLocation) {
                            longTap(it.location.x, it.location.y)
                        }
                    }
                    "На странице присутствует 1-ый рекламный текст" {
                        waitForElement(LandingPage.ads1stLineLocation)
                    }
                }
            }
        }
    }

}