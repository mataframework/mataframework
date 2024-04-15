package com.github.mataframework.junit

import com.github.mataframework.app.App
import com.github.mataframework.pages.PageObject

/**
 * Interface provides an ability to do some actions before application close, but after all tests are done.
 */
interface AfterAllProcessor {
    /**
     * Called after all tests executed, but before application was closed.
     *
     * @param app - instance of the application
     * @param pageObject - instance of the page object
     */
    fun doAfterAll(app: App?, pageObject: PageObject?)
}