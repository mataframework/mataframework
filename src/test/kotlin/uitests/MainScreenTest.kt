package uitests

import com.github.mataframework.junit.MataTest
import com.github.mataframework.junit.MataTestSuit
import com.github.mataframework.junit.spec.MataTestCase
import com.github.mataframework.pages.LookupConfig
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.TestMethodOrder
import skips.SkipLanding
import uitests.ui.LandingPage
import kotlin.test.assertTrue

@MataTestSuit(
    appStartUpProcessors = [SkipLanding::class]
)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class MainScreenTest : MataTestCase() {

    @Order(1)
    @MataTest
    fun checkOpenApp() {
        val longWaitConfig = LookupConfig(30000)

        mataTest {
            "Главная страница" {
                screenshot("Главная страница")
                "В верхнем меню активна кнопка 'Моё кино'" {
                    waitForElement(LandingPage.TopNavigation.myCinemaLocation, longWaitConfig) {
                        screenshot(it, "Элемент верхнего меню")
                        assertEquals("true", it.getAttribute("focused"))
                    }
                }
                "В нижнем меню активна кнопка 'Главная'" {
                    waitForElement(LandingPage.BottomNavigation.hdLocation) {
                        assertTrue { it.isSelected }
                        assertTrue { it.isEnabled }
                    }
                }
                "На странице присутствует 1-ый рекламный текст" {
                    waitForElement(LandingPage.ads1stLineLocation)
                }
                "На странице присутствует 2-ой рекламный текст" {
                    waitForElement(LandingPage.ads2ndLineLocation)
                }
                "На странице присутствует кнопка 'Попробовать бесплатно'" {
                    waitForElement(LandingPage.tryFreeButtonLocation)
                }
                "На странице присутствует информационный блок" {
                    waitForElement(LandingPage.infoBlockLocation)
                }
            }
        }
    }

    @Order(2)
    @MataTest(recordExecution = true)
    fun checkDoSomething() {
        pageObject
            .waitForElement(LandingPage.BottomNavigation.hdLocation) {
                pageObject.tap(it.location.x, it.location.y)
            }
            .waitForElement(LandingPage.ads1stLineLocation)
            .waitForElement(LandingPage.BottomNavigation.hdLocation) {
                pageObject.longTap(it.location.x, it.location.y)
            }
            .waitForElement(LandingPage.ads1stLineLocation)
    }

}