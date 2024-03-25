package pages

import app.App
import org.openqa.selenium.By

class MainScreen(app: App): PageObject(app) {

    val exampleButton = choose(
        By.id("loginButton"),
        By.id("")
    )
}