package com.github.mataframework.junit

import org.junit.jupiter.api.Test

/**
 * Annotation describe MATA test.
 */
@Test
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class MataTest(
    val cleanRun: Boolean = false,
    val screenshotOnFail: Boolean = true,
    val recordExecution: Boolean = false,
)
