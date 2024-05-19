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
    val screenshotOnFailName: String = "Fail Screenshot",
    val recordExecution: Boolean = false,
    val recordExecutionName: String = "Recording Test Run Screen",
)
