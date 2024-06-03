package skips

import com.github.mataframework.spec.MataTestSpec
import com.github.mataframework.spec.listener.AppStartUpListener
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
                waitForElementOrNull(PlusAdsPage.skipButtonLocation) {
                    "Пропускаем 3-ий рекламный блок" {
                        click(it)
                    }
                }
                waitForElementOrNull(PlusAdsPage.skipVPNButtonLocation) {
                    "Пропускаем 3-ий рекламный блок под VPN" {
                        click(it)
                    }
                }
            }
        }
    }

}
