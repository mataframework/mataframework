package skips

import com.github.mataframework.app.App
import com.github.mataframework.junit.AppStartUpListener
import com.github.mataframework.pages.PageObject
import uitests.ui.IntroductionPage
import uitests.ui.PlusAdsPage

object SkipLanding : AppStartUpListener {

    override fun onAppStartUp(app: App<*>, pageObject: PageObject) {
        pageObject
            .waitForElementAndClick(IntroductionPage.nextButtonLocation)
            .waitForElementAndClick(IntroductionPage.nextButtonLocation)
            .waitForElementAndClick(PlusAdsPage.skipButtonLocation)
    }
}