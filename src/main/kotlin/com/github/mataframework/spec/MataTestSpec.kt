package com.github.mataframework.spec

import com.github.mataframework.app.App
import com.github.mataframework.app.Configuration
import com.github.mataframework.app.Platform
import com.github.mataframework.exception.MataFrameworkException
import com.github.mataframework.pages.PageObject
import com.github.mataframework.spec.option.ScreenShotOnFailOption
import io.qameta.allure.Allure
import org.openqa.selenium.OutputType
import java.util.*

class MataTestSpec(
    private val app: App<*>,
    private val pageObject: PageObject,
    private val screenShotOnFailOption: ScreenShotOnFailOption
) {
    private var currentStepTrace: MutableList<Int> = mutableListOf(1)
    private var screenShotThrowable: MutableList<Throwable> = mutableListOf()
    private var currentStepName: String = ""
    private var recordIsOn: Boolean = false

    operator fun String.invoke(scenery: PageObject.() -> Unit) {
        currentStepName = this
        step(currentStepName) {
            currentStepTrace.add(1)
            try {
                pageObject.scenery()
            } catch (e: Throwable) {
                if (!screenShotThrowable.contains(e) && screenShotOnFailOption.enabled) {
                    val screenshot = app.getScreenshotAs(OutputType.BYTES)
                    Allure.addByteAttachmentAsync(screenShotOnFailOption.name, "image/png") { screenshot }
                    screenShotThrowable.add(e)
                }
                throw e
            } finally {
                currentStepTrace.removeLast()
            }
        }

        val currentStep = currentStepTrace.removeLast() + 1
        currentStepTrace.add(currentStep)
    }

    operator fun Platform.invoke(scenery: PageObject.() -> Unit) {
        if (Configuration.getPlatform() != this) return
        pageObject.scenery()
    }

    fun screenshot(name: String) {
        val screenshot = app.getScreenshotAs(OutputType.BYTES)

        Allure.addByteAttachmentAsync(name, "image/png") { screenshot }
    }

    fun screenshot() {
        screenshot(currentStepName)
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

    fun hideKeyboard() {
        app.hideKeyboard()
    }

    fun step(): Int {
        return currentStepTrace.last()
    }

    fun stepTrace(offset: Int = 0): List<Int> {
        return Collections.unmodifiableList(currentStepTrace)
            .drop(offset)
    }

    fun stepTrace(
        separator: String,
        offset: Int = 0
    ): String {
        return stepTrace(offset)
            .joinToString(separator = separator) { it.toString() }
    }

    private fun step(description: String, action: Allure.ThrowableRunnableVoid) {
        Allure.step(description, action)
    }
}
