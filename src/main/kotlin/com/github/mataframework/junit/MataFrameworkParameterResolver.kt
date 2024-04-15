package com.github.mataframework.junit

import com.github.mataframework.app.App
import com.github.mataframework.pages.PageObject
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolutionException
import org.junit.jupiter.api.extension.ParameterResolver

class MataFrameworkParameterResolver : ParameterResolver {
    @Throws(ParameterResolutionException::class)
    override fun supportsParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Boolean {
        val type = parameterContext.parameter.type
        return type == PageObject::class.java || type == App::class.java
    }

    @Throws(ParameterResolutionException::class)
    override fun resolveParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Any {
        val type = parameterContext.parameter.type
        val store = extensionContext.getStore(MataTestEngine.MATA_FRAMEWORK)
        if(type == App::class.java) {
            return store.get(MataTestEngine.Companion.StorageKeys.APP)
        }
        if(type == PageObject::class.java) {
            return store.get(MataTestEngine.Companion.StorageKeys.PAGE_OBJECT)
        }
        throw ParameterResolutionException("Unsupported parameter type: $type")
    }
}
