package uitests.ui

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.FluentWait

class IntroductionPage(driver: FluentWait<AppiumDriver<MobileElement>?>) {
    private val contentContainerLocation = By.xpath("//android.view.ViewGroup[@resource-id=\"ru.kinopoisk:id/container\"]")
    private val descriptionLocation = By.id("ru.kinopoisk:id/description")
    private val nextButtonLocation = By.id("ru.kinopoisk:id/button_next")


    private val contentContainerElement: WebElement = driver.until(ExpectedConditions.visibilityOfElementLocated(contentContainerLocation))
    private val descriptionElement: WebElement = contentContainerElement.findElement(descriptionLocation)
    private val nextButtonElement: WebElement = contentContainerElement.findElement(nextButtonLocation)

    fun clickNext() {
        nextButtonElement.click()
    }

    fun getDescription() : String {
        return descriptionElement.text
    }

}