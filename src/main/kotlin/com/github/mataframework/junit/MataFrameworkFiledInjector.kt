package com.github.mataframework.junit

import com.github.mataframework.app.App
import com.github.mataframework.exception.MataFrameworkException
import com.github.mataframework.junit.MataTestEngine.Companion.MATA_FRAMEWORK
import com.github.mataframework.pages.PageObject
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.platform.commons.support.AnnotationSupport

class MataFrameworkFiledInjector : BeforeEachCallback {

    override fun beforeEach(context: ExtensionContext?) {
        val store = context?.getStore(MATA_FRAMEWORK) ?: return

        val app = store.get(MataTestEngine.Companion.StorageKeys.APP) as App?
        val pageObject = store.get(MataTestEngine.Companion.StorageKeys.PAGE_OBJECT) as PageObject?

        val fields = context.testClass?.map {
            AnnotationSupport.findAnnotatedFields(it, MataInject::class.java)
        }?.orElse(null) ?: emptyList()

        val allInstances = context.testInstances.map {
            it.allInstances
        }.orElse(null) ?: emptyList()

        for (field in fields) {
            field.isAccessible = true
            for (instance in allInstances) {
                when (field.type) {
                    PageObject::class.java -> {
                        field.set(instance, pageObject)
                    }

                    App::class.java -> {
                        field.set(instance, app)
                    }

                    else -> {
                        throw MataFrameworkException("${field.type} is not supported.")
                    }
                }
            }
        }
    }

}
