package com.github.mataframework.junit

import com.github.mataframework.app.App
import com.github.mataframework.app.AppLauncher
import com.github.mataframework.exception.MataFrameworkException
import com.github.mataframework.pages.PageObject
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ExtensionContext.Namespace
import org.junit.jupiter.api.extension.ExtensionContext.Store
import org.junit.platform.commons.support.AnnotationSupport

class MataTestEngine : BeforeAllCallback, BeforeEachCallback, AfterAllCallback {

    @Throws(Exception::class)
    override fun beforeAll(context: ExtensionContext) {
        val testClass = context.requiredTestClass
        val mataSpecification = AnnotationSupport.findAnnotation(testClass, MataTestSuit::class.java)
            .orElseThrow { MataFrameworkException("MataTestSuitSpecification annotation not found.") }

        val store = context.getStore(MATA_FRAMEWORK)
        store.put(StorageKeys.MATA_SPECIFICATION, mataSpecification)

        val appLauncher = AppLauncher()
        store.put(StorageKeys.APP_LAUNCHER, appLauncher)
    }

    override fun beforeEach(context: ExtensionContext?) {
        val classContext = context?.parent?.get() ?: return
        val store = classContext.getStore(MATA_FRAMEWORK) ?: return

        val cleanRun = context.testMethod
            .flatMap { AnnotationSupport.findAnnotation(it, MataTest::class.java) }
            .map { it.cleanRun }
            .orElse(false)

        val app = store.get(StorageKeys.APP) as App?
        val mataSpecification = store.get(StorageKeys.MATA_SPECIFICATION) as MataTestSuit

        if (cleanRun || app == null) {
            if (app != null) {
                val pageObject = store.get(StorageKeys.PAGE_OBJECT) as PageObject?
                shutDownApp(app, pageObject, mataSpecification)
            }

            startUpApp(cleanRun, store, mataSpecification)
        }
    }

    override fun afterAll(context: ExtensionContext?) {
        val store = context?.getStore(MATA_FRAMEWORK) ?: return

        val app = store.get(StorageKeys.APP) as App?
        val pageObject = store.get(StorageKeys.PAGE_OBJECT) as PageObject?

        val mataSpecification = store.get(StorageKeys.MATA_SPECIFICATION) as MataTestSuit

        shutDownApp(app, pageObject, mataSpecification)
    }

    private fun startUpApp(
        cleanRun: Boolean,
        store: Store,
        mataSpecification: MataTestSuit
    ) {
        val appLauncher = store.get(StorageKeys.APP_LAUNCHER) as AppLauncher
        val app = appLauncher.launch(cleanRun)
        val pageObject = PageObject(app)

        store.put(StorageKeys.APP, app)
        store.put(StorageKeys.PAGE_OBJECT, pageObject)

        for (listenerKClass in mataSpecification.appStartUpProcessors) {
            val listener = listenerKClass.objectInstance
            listener?.onAppStartUp(app, pageObject)
                ?: throw MataFrameworkException("${listenerKClass.qualifiedName} is not object.")
        }
    }

    private fun shutDownApp(
        app: App?,
        pageObject: PageObject?,
        mataSpecification: MataTestSuit
    ) {
        for (listenerKClass in mataSpecification.appShutDownListener) {
            val listener = listenerKClass.objectInstance
            listener?.onAppShutDown(app, pageObject)
                ?: throw MataFrameworkException("${listenerKClass.qualifiedName} is not object.")
        }

        app?.close()
    }

    companion object {
        @JvmStatic
        val MATA_FRAMEWORK: Namespace = Namespace.create("com.github.mataframework")

        enum class StorageKeys {
            APP_LAUNCHER,
            APP,
            PAGE_OBJECT,
            MATA_SPECIFICATION
        }
    }

}
