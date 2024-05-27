package com.github.mataframework.spec.listener

import com.github.mataframework.spec.MataTestSpec

/**
 * Interface provides an ability to do some actions before application close, but after test are done.
 */
interface AppShutDownListener {
    /**
     * Called before application was closed.
     *
     * @param spec - active test spec.
     */
    fun onAppShutDown(spec: MataTestSpec)
}