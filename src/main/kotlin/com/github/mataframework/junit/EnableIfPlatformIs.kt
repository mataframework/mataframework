package com.github.mataframework.junit

import com.github.mataframework.app.Platform
import org.junit.jupiter.api.extension.ExtendWith

/**
 * Annotation allow mark tests as platform specified
 */
@ExtendWith(PlatformExecutionCondition::class)
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class EnableIfPlatformIs(
    val value: Platform
)
