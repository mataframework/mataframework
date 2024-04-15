package com.github.mataframework.junit.condition

import com.github.mataframework.app.Platform
import org.junit.jupiter.api.extension.ExtendWith

/**
 * Mark-annotation for tests as a platform specified
 */
@ExtendWith(PlatformExecutionCondition::class)
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class EnableIfPlatformIs(
    val value: Platform
)
