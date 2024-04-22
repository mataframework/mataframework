package com.github.mataframework.junit

import org.junit.jupiter.api.extension.ExtendWith
import kotlin.reflect.KClass

/**
 * Annotation describe MATA test suit.
 */
@ExtendWith(
    MataTestEngine::class,
    MataFrameworkFiledInjector::class,
    MataFrameworkParameterResolver::class
)
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class MataTestSuit(
    val appStartUpProcessors: Array<KClass<out AppStartUpListener>> = [],
    val appShutDownListener: Array<KClass<out AppShutDownListener>> = []
)
