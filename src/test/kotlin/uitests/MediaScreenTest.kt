package uitests

import com.github.mataframework.spec.option.AppOptions
import com.github.mataframework.spec.mataTest
import com.github.mataframework.pages.LookupConfig
import com.github.mataframework.pages.scroll.ScrollAction
import com.github.mataframework.pages.scroll.ScrollDirection
import org.junit.jupiter.api.Test
import skips.SkipLanding
import uitests.ui.LandingPage
import uitests.ui.MediaPage

class MediaScreenTest {
    @Test
    fun checkOpenApp() {
        val longWaitConfig = LookupConfig(30000)
        val longWaitAndScrollConfig = LookupConfig(
            1000,
            scrollAction = ScrollAction(100, scrollTimes = 10),
            fitRequired = true
        )

        mataTest(
            appOptions = AppOptions(cleanRun = true),
            appStartUpListeners = arrayOf(SkipLanding::class)
        ) {
            "Переходим на страницу 'Афиши'" {
                waitForElement(LandingPage.BottomNavigation.mediaLocation, longWaitConfig) {
                    click(it)
                }
            }
            "Афиша" {
                "На странице присутствуют фильмы, которые скоро выйдут" {
                    waitForElement(
                        MediaPage.soonFilmsCarouselElement,
                        longWaitAndScrollConfig
                    ) {
                        "Эти фильмы можно скроллить вниз" {
                            scroll(
                                it,
                                ScrollAction(100)
                            )
                        }
                        "Эти фильмы можно скроллить вправо" {
                            scroll(
                                it,
                                ScrollAction(100, ScrollDirection.RIGHT)
                            )
                        }
                    }
                }
                "На странице присутствуют трейлеры" {
                    waitForElement(MediaPage.trailersCarouselElement, longWaitAndScrollConfig) {
                        "Трейлеры можно скроллить" {
                            scroll(
                                it,
                                ScrollAction(100, ScrollDirection.RIGHT)
                            )
                        }
                    }
                }
            }
        }
    }

}