package uitests

import com.github.mataframework.junit.MataInject
import com.github.mataframework.junit.MataTest
import com.github.mataframework.junit.MataTestSuit
import com.github.mataframework.pages.PageObject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.TestMethodOrder
import skips.SkipLanding
import uitests.ui.LandingPage
import kotlin.test.assertTrue

@MataTestSuit(
    beforeEachStartUpProcessors = [SkipLanding::class]
)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class MainScreenTest {
    @MataInject
    private lateinit var pageObject: PageObject

    @Order(1)
    @MataTest
    fun checkOpenApp() {
        pageObject
            .waitForElement(LandingPage.TopNavigation.myCinemaLocation, 30000) {
                assertEquals("true", it.getAttribute("focused"))
            }
            .waitForElement(LandingPage.BottomNavigation.hdLocation) {
                assertTrue { it.isSelected }
                assertTrue { it.isEnabled }
            }
            .waitForElement(LandingPage.ads1stLineLocation)
            .waitForElement(LandingPage.ads2ndLineLocation)
            .waitForElement(LandingPage.tryFreeButtonLocation)
            .waitForElement(LandingPage.infoBlockLocation)
    }

    @Order(2)
    @MataTest(cleanRun = false)
    fun checkDoSomething() {
        pageObject
            .waitForElement(LandingPage.TopNavigation.myCinemaLocation, 30000) {
                assertEquals("true", it.getAttribute("focused"))
            }
            .waitForElement(LandingPage.BottomNavigation.hdLocation) {
                assertTrue { it.isSelected }
                assertTrue { it.isEnabled }
            }
            .waitForElement(LandingPage.ads1stLineLocation)
            .waitForElement(LandingPage.ads2ndLineLocation)
            .waitForElement(LandingPage.tryFreeButtonLocation)
            .waitForElement(LandingPage.infoBlockLocation)
    }

}