package com.github.mataframework.app

import com.github.mataframework.exception.MataFrameworkException

/**
 *
 * @author sibmaks
 * @since 0.0.1
 */
class PlatformProperty<T>(private val config: Map<Platform, T>) {

    constructor(androidValue: T, iOSValue: T) : this(
        mapOf(
            Platform.ANDROID to androidValue,
            Platform.IOS to iOSValue,
        )
    )

    fun getValue(): T {
        val platform = Configuration.getPlatform()
        return config[platform] ?: throw MataFrameworkException("Property for platform '$platform' not found")
    }

    companion object {
        @JvmStatic
        fun <V> of(vararg pairs: Pair<Platform, V>): PlatformProperty<V> {
            return PlatformProperty(mapOf(*pairs))
        }
    }

}