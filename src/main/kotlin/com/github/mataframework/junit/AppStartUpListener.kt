package com.github.mataframework.junit

import com.github.mataframework.app.App
import com.github.mataframework.pages.PageObject

/**
 * Interface provides an ability to do some actions after the application started, but before test is started.
 */
interface AppStartUpListener {
    /**
     * Called after application start up.
     *
     * @param app - instance of the application
     * @param pageObject - instance of the page object
     */
    fun onAppStartUp(app: App<*>, pageObject: PageObject)
}