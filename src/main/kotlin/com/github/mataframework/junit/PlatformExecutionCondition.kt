package com.github.mataframework.junit

import com.github.mataframework.app.Configuration
import com.github.mataframework.app.Platform
import org.junit.jupiter.api.extension.ConditionEvaluationResult
import org.junit.jupiter.api.extension.ExecutionCondition
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.platform.commons.support.AnnotationSupport

class PlatformExecutionCondition : ExecutionCondition {
    override fun evaluateExecutionCondition(context: ExtensionContext?): ConditionEvaluationResult {
        if (context == null) {
            return ConditionEvaluationResult.enabled("No context")
        }
        val platform = Configuration.getPlatform()
        val enabledPlatform = getEnabledPlatform(context)
        if (platform == enabledPlatform) {
            return ConditionEvaluationResult.enabled("Platform is $enabledPlatform")
        }
        return ConditionEvaluationResult.disabled("Platform is not $enabledPlatform")
    }

    private fun getEnabledPlatform(context: ExtensionContext): Platform? {
        return context.element.flatMap {
            AnnotationSupport.findAnnotation(it, EnableIfPlatformIs::class.java)
        }
            .map { it.value }
            .orElse(null)
    }
}