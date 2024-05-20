package com.github.mataframework.spec

import com.github.mataframework.app.AppLauncher
import com.github.mataframework.exception.MataFrameworkException
import com.github.mataframework.pages.PageObject
import com.github.mataframework.spec.listener.AppShutDownListener
import com.github.mataframework.spec.listener.AppStartUpListener
import com.github.mataframework.spec.option.AppOptions
import com.github.mataframework.spec.option.ScreenShotOnFailOption
import io.qameta.allure.Allure
import org.openqa.selenium.OutputType
import kotlin.reflect.KClass


fun mataTest(
    appOptions: AppOptions = AppOptions(),
    screenShotOnFailOption: ScreenShotOnFailOption = ScreenShotOnFailOption(),
    appStartUpListeners: Array<KClass<out AppStartUpListener>> = arrayOf(),
    appShutDownListeners: Array<KClass<out AppShutDownListener>> = arrayOf(),
    test: MataTestSpec.() -> Unit
) {
    val appLauncher = AppLauncher()
    val app = appLauncher.launch(appOptions.cleanRun)
    val pageObject = PageObject(app)

    try {
        val context = MataTestSpec(app, pageObject)

        for (listenerKClass in appStartUpListeners) {
            val listener = listenerKClass.objectInstance
            listener?.onAppStartUp(context)
                ?: throw MataFrameworkException("${listenerKClass.qualifiedName} is not object.")
        }

        context.test()

        for (listenerKClass in appShutDownListeners) {
            val listener = listenerKClass.objectInstance
            listener?.onAppShutDown(context)
                ?: throw MataFrameworkException("${listenerKClass.qualifiedName} is not object.")
        }
    } catch (e: Throwable) {
        if (screenShotOnFailOption.enabled) {
            val screenshot = app.getScreenshotAs(OutputType.BYTES)
            Allure.addByteAttachmentAsync(screenShotOnFailOption.name, "image/png") { screenshot }
        }
        throw e
    } finally {
        app.close()
    }
}
