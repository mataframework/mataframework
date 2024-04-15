package com.github.mataframework.junit

import com.github.mataframework.app.App
import com.github.mataframework.pages.PageObject

interface BeforeAllProcessor {
    fun prepare(app: App, pageObject: PageObject)
}