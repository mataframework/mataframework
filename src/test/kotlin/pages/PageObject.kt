package pages

import app.App
import app.Configuration
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import org.openqa.selenium.By

open class PageObject(val app: App) {

    protected val driver: AppiumDriver<MobileElement>? = app.driver

    protected val app_id: String = choiceText("//TODO package app", "//TODO package app")

    companion object {
        fun by(android: By, ios: By): By {
            return if (Configuration.isAndroid()) android else ios
        }

        fun choiceText(android: String, ios: String): String {
            return if (Configuration.isAndroid()) android else ios
        }
    }

    /*
     * Elements actions
     */

    fun terminateApp() {
        driver!!.terminateApp(app_id)
    }

}