package uitests.ui

import app.PlatformProperty

/**
 *
 * @author sibmaks
 * @since 0.0.1
 */
object UIAttributes {

    val focused: PlatformProperty<String> = PlatformProperty(
        "focused",
        "focused" //TODO: fill for iOS
    )

    val selected: PlatformProperty<String> = PlatformProperty(
        "selected",
        "selected" //TODO: fill for iOS
    )
}