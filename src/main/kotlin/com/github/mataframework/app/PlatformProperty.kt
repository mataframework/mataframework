package com.github.mataframework.app

import com.github.mataframework.exception.MataFrameworkException

/**
 *
 * @author sibmaks
 * @since 0.0.1
 */
class PlatformProperty<T>(vararg pairs: Pair<Platform, T>) {
    private val config = mutableMapOf(*pairs)

    constructor(androidValue: T, iOSValue: T) : this(
        Platform.ANDROID to androidValue,
        Platform.IOS to iOSValue,
    )

    /**
     * Get value for active platform.
     *
     * @throws MataFrameworkException throws if there are no value for active platform
     */
    fun get(): T {
        val platform = Configuration.getPlatform()
        return get(platform)
    }

    /**
     * Get value for [platform].
     *
     * @throws MataFrameworkException throws if there are no value for [platform]
     */
    fun get(platform: Platform): T {
        return config[platform] ?: throw MataFrameworkException("Property for platform '$platform' not found")
    }

    /**
     * Set value for [platform].
     */
    fun set(platform: Platform, value: T) {
        config[platform] = value
    }

}