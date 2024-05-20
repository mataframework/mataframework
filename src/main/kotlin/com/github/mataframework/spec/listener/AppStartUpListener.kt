package com.github.mataframework.spec.listener

import com.github.mataframework.spec.MataTestSpec

/**
 * Interface provides an ability to do some actions after the application started, but before test is started.
 */
interface AppStartUpListener {
    /**
     * Called after application start up.
     *
     * @param spec - active test spec.
     */
    fun onAppStartUp(spec: MataTestSpec)
}