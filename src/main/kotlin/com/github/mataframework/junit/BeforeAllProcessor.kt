package com.github.mataframework.junit

import com.github.mataframework.app.App
import com.github.mataframework.pages.PageObject

/**
 * Interface provides an ability to do some actions after the application started, but before any tests are started.
 */
interface BeforeAllProcessor {
    /**
     * Called after the application started, but before any tests are started.
     *
     * @param app - instance of the application
     * @param pageObject - instance of the page object
     */
    fun doBeforeAll(app: App, pageObject: PageObject)
}