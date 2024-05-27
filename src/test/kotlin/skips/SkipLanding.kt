package skips

import com.github.mataframework.spec.listener.AppStartUpListener
import com.github.mataframework.spec.MataTestSpec
import uitests.ui.IntroductionPage
import uitests.ui.PlusAdsPage

object SkipLanding : AppStartUpListener {

    override fun onAppStartUp(spec: MataTestSpec) {
        spec.apply {
            "Пропуск вступительного экрана" {
                "Пропускаем 1-ый рекламный блок" {
                    waitForElement(IntroductionPage.nextButtonLocation) {
                        click(it)
                    }
                }
                "Пропускаем 2-ой рекламный блок" {
                    waitForElement(IntroductionPage.nextButtonLocation) {
                        click(it)
                    }
                }
                "Пропускаем 3-ий рекламный блок" {
                    waitForElement(PlusAdsPage.skipButtonLocation) {
                        click(it)
                    }
                }
            }
        }
    }

}
