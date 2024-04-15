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
        val mataSpecification = AnnotationSupport.findAnnotation(testClass, MataTestSuitSpecification::class.java)
            .orElseThrow { MataFrameworkException("MataTestSuitSpecification annotation not found.") }

        val store = context.getStore(MATA_FRAMEWORK)
        store.put(StorageKeys.MATA_SPECIFICATION, mataSpecification)

        val appLauncher = AppLauncher()
        val app = appLauncher.launch()
        val pageObject = PageObject(app)

        store.put(StorageKeys.APP_LAUNCHER, appLauncher)
        store.put(StorageKeys.APP, app)
        store.put(StorageKeys.PAGE_OBJECT, pageObject)

        for (beforeAllProcessor in mataSpecification.beforeAllProcessors) {
            val processorInstance = beforeAllProcessor.objectInstance
            processorInstance?.doBeforeAll(app, pageObject)
                ?: throw MataFrameworkException("${beforeAllProcessor.qualifiedName} is not object.")
        }
    }

    override fun afterAll(context: ExtensionContext?) {
        val store = context?.getStore(MATA_FRAMEWORK) ?: return

        val app = store.get(StorageKeys.APP) as App?
        val pageObject = store.get(StorageKeys.PAGE_OBJECT) as PageObject?

        val mataSpecification = store.get(StorageKeys.MATA_SPECIFICATION) as MataTestSuitSpecification
        for (afterAllProcessor in mataSpecification.afterAllProcessors) {
            val processorInstance = afterAllProcessor.objectInstance
            processorInstance?.doAfterAll(app, pageObject)
                ?: throw MataFrameworkException("${afterAllProcessor.qualifiedName} is not object.")
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
