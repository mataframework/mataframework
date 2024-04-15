package com.github.mataframework.junit

import org.junit.jupiter.api.extension.ExtendWith
import kotlin.reflect.KClass

/**
 * Annotation describe MATA test.
 */
@ExtendWith(MataTestEngine::class, MataFrameworkParameterResolver::class)
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class MataTestSuitSpecification(
    val beforeAllProcessors: Array<KClass<out BeforeAllProcessor>> = []
)
