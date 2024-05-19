package com.github.mataframework.junit.spec

import com.github.mataframework.app.App
import com.github.mataframework.pages.PageObject
import io.qameta.allure.Allure
import org.openqa.selenium.OutputType
import org.openqa.selenium.remote.RemoteWebElement

class MataTestSpec(
    val app: App<*>,
    val pageObject: PageObject
) {
    private var currentStep: Int = 1

    operator fun String.invoke(scenery: PageObject.() -> Unit) {
        step(
            this.replace("{step}", currentStep.toString()),
        ) {
            pageObject.scenery()
        }
        currentStep++
    }

    fun screenshot(name: String) {
        val screenshot = app.driver.getScreenshotAs(OutputType.BYTES)

        Allure.addByteAttachmentAsync(name, "image/png") { screenshot }
    }

    fun screenshot(element: RemoteWebElement, name: String) {
        val screenshot = element.getScreenshotAs(OutputType.BYTES)

        Allure.addByteAttachmentAsync(name, "image/png") { screenshot }
    }

    private fun step(description: String, action: Allure.ThrowableRunnableVoid) {
        Allure.step(description, action)
    }
}
