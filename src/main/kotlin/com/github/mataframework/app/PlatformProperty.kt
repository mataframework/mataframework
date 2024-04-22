package com.github.mataframework.app

/**
 *
 * @author sibmaks
 * @since 0.0.1
 */
class PlatformProperty<T>(private val androidValue: T, private val iOSValue: T) {

    fun getValue(): T {
        return when (Configuration.getPlatform()) {
            Platform.ANDROID -> androidValue
            Platform.IOS -> iOSValue
        }

    }

}