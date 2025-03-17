package com.tutorial.mpaaspoc

import android.app.Application
import android.util.Log
import com.mpaas.android.mPaaS
import com.mpaas.core.MP

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        mPaaS(this){
            callback { Log.d("MPaaS", "MPaaS init success") }
        }
        MP.init(this)
    }

}