package com.github.mataframework.pages

import com.github.mataframework.app.Configuration
import com.github.mataframework.pages.scroll.ScrollAction

data class LookupConfig(
    val timeout: Long = Configuration.getElementPollingTimeout(),
    val interval: Long = Configuration.getElementPollingInterval(),
    val scrollAction: ScrollAction = ScrollAction(0, scrollTimes = 0),
    val fitRequired: Boolean = false
)