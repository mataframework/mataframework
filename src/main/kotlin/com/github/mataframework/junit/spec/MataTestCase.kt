package com.github.mataframework.junit.spec

import com.github.mataframework.app.App
import com.github.mataframework.junit.MataInject
import com.github.mataframework.pages.PageObject

abstract class MataTestCase {

    @MataInject
    protected lateinit var app: App<*>

    @MataInject
    protected lateinit var pageObject: PageObject

    protected fun mataTest(
        test: MataTestSpec.() -> Unit
    ) {
        val context = MataTestSpec(app, pageObject)
        context.test()
    }

}