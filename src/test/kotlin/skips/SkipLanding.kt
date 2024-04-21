package skips

import com.github.mataframework.app.App
import com.github.mataframework.junit.BeforeEachStartUpProcessor
import com.github.mataframework.pages.PageObject
import uitests.ui.IntroductionPage
import uitests.ui.PlusAdsPage

object SkipLanding : BeforeEachStartUpProcessor {

    override fun doBeforeAll(app: App, pageObject: PageObject) {
        pageObject
            .waitForElementAndClick(IntroductionPage.nextButtonLocation)
            .waitForElementAndClick(IntroductionPage.nextButtonLocation)
            .waitForElementAndClick(PlusAdsPage.skipButtonLocation)
    }
}