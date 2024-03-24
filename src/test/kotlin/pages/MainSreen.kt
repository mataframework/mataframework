package pages

import app.App
import org.openqa.selenium.By

class MainScreen(app: App): PageObject(app) {

    val exampleButton = by(
        By.id("loginButton"),
        By.id("")
    )
}