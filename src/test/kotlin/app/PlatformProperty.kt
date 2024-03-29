package app

/**
 *
 * @author sibmaks
 * @since 0.0.1
 */
class PlatformProperty<T>(val androidValue: T, val iOSValue: T) {

    fun getValue(): T {
        if (Configuration.isAndroid()) {
            return androidValue
        }
        return iOSValue

    }

}