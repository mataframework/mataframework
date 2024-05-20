package com.github.mataframework.spec

import com.github.mataframework.app.App
import com.github.mataframework.exception.MataFrameworkException
import com.github.mataframework.pages.PageObject
import io.qameta.allure.Allure
import org.openqa.selenium.OutputType
import org.openqa.selenium.remote.RemoteWebElement

class MataTestSpec(
    val app: App<*>,
    private val pageObject: PageObject
) {
    private var currentStep: Int = 1
    private var currentStepName: String = ""
    private var recordIsOn: Boolean = false

    operator fun String.invoke(scenery: PageObject.() -> Unit) {
        currentStepName = this.replace("{step}", currentStep.toString())
        step(currentStepName) {
            pageObject.scenery()
        }
        currentStep++
    }

    fun screenshot(name: String) {
        val screenshot = app.getScreenshotAs(OutputType.BYTES)

        Allure.addByteAttachmentAsync(name, "image/png") { screenshot }
    }

    fun screenshot(element: RemoteWebElement, name: String) {
        val screenshot = element.getScreenshotAs(OutputType.BYTES)

        Allure.addByteAttachmentAsync(name, "image/png") { screenshot }
    }

    fun record(inner: MataTestSpec.() -> Unit) {
        record(currentStepName, inner)
    }

    fun record(name: String, inner: MataTestSpec.() -> Unit) {
        if (recordIsOn) {
            throw MataFrameworkException("Can't record more than one step")
        }
        app.startRecordingScreen()
        recordIsOn = true
        try {
            inner()
        } finally {
            app.stopRecordingScreen(name)
            recordIsOn = false
        }
    }

    private fun step(description: String, action: Allure.ThrowableRunnableVoid) {
        Allure.step(description, action)
    }
}
