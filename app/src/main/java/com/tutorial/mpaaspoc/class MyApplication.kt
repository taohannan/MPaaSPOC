package com.tutorial.mpaaspoc

import android.app.Application
import android.util.Log
import com.mpaas.android.mPaaS
import com.mpaas.core.MP
import com.mpaas.core.MPInitParam
import com.mpaas.mriver.api.resource.MriverResource

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MriverResource.disableVerify()

        // Initialize Mriver Mini App support
        val mriverInitParam = com.mpaas.mriver.api.init.MriverInitParam.getDefault()
        mriverInitParam.setMriverInitCallback(object : com.mpaas.mriver.api.init.MriverInitParam.MriverInitCallback {
            override fun onInit() {
                if (com.alibaba.ariver.kernel.common.utils.ProcessUtils.isMainProcess()) {
                    Log.d("Mriver", "Mini App ready to launch.")
                }
            }

            override fun onError(e: Exception?) {
                Log.e("Mriver", "Mini App init failed", e)
            }
        })

        // Now initialize MP with Mriver configuration
        MP.init(
            this,
            MPInitParam.obtain().addComponentInitParam(mriverInitParam)
        )

        // Optional Kotlin DSL init success log
        mPaaS(this) {
            callback { Log.wtf("MPaaS", "MPaaS init success") }
        }
    }
}
