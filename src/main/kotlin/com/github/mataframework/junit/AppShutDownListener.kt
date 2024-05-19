package com.github.mataframework.junit

import com.github.mataframework.app.App
import com.github.mataframework.pages.PageObject

/**
 * Interface provides an ability to do some actions before application close, but after test are done.
 */
interface AppShutDownListener {
    /**
     * Called before application was closed.
     *
     * @param app - instance of the application
     * @param pageObject - instance of the page object
     */
    fun onAppShutDown(app: App<*>?, pageObject: PageObject?)
}