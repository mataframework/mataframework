package com.github.mataframework.junit

import com.github.mataframework.app.App
import com.github.mataframework.app.AppLauncher
import com.github.mataframework.exception.MataFrameworkException
import com.github.mataframework.pages.PageObject
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ExtensionContext.Namespace
import org.junit.platform.commons.support.AnnotationSupport

class MataTestEngine : BeforeAllCallback, AfterAllCallback {

    @Throws(Exception::class)
    override fun beforeAll(context: ExtensionContext) {
        val testClass = context.requiredTestClass
        val mataTestSuitSpecification =
            AnnotationSupport.findAnnotation(testClass, MataTestSuitSpecification::class.java)
                .orElseThrow { MataFrameworkException("MataTestSuitSpecification annotation not found.") }

        val store = context.getStore(MATA_FRAMEWORK)

        val appLauncher = AppLauncher()
        val app = appLauncher.launch()
        val pageObject = PageObject(app)

        store.put(StorageKeys.APP_LAUNCHER, appLauncher)
        store.put(StorageKeys.APP, app)
        store.put(StorageKeys.PAGE_OBJECT, pageObject)

        for (beforeAllProcessor in mataTestSuitSpecification.beforeAllProcessors) {
            beforeAllProcessor.objectInstance?.prepare(app, pageObject)
                ?: throw MataFrameworkException("${beforeAllProcessor.qualifiedName} is not object.")
        }
    }

    companion object {
        @JvmStatic
        val MATA_FRAMEWORK: Namespace = Namespace.create("com.github.mataframework")

        enum class StorageKeys {
            APP_LAUNCHER,
            APP,
            PAGE_OBJECT
        }
    }

    override fun afterAll(context: ExtensionContext?) {
        val store = context?.getStore(MATA_FRAMEWORK)
        val app = store?.get(StorageKeys.APP) as App?
        app?.close()
    }
}
