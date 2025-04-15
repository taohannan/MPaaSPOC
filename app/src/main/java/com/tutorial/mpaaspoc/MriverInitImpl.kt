package com.tutorial.mpaaspoc

import android.content.Context
import com.alibaba.ariver.kernel.common.utils.ProcessUtils
import com.mpaas.MPInitParamManifest
import com.mpaas.core.MPInitParam
import com.mpaas.mriver.api.init.MriverInitParam
import com.mpaas.mriver.api.resource.MriverResource
import java.lang.Exception

class MriverInitImpl :MPInitParamManifest {
    override fun initParam(context: Context): MPInitParam {
       val mriverInitParam = MriverInitParam.getDefault()
        
        mriverInitParam.setMriverInitCallback(object : MriverInitParam.MriverInitCallback {
            override fun onInit() {
                if(ProcessUtils.isMainProcess()){

                }
                MriverResource.disableVerify()
            }

            override fun onError(p0: Exception?) {
                p0?.printStackTrace()
            }

        })

        return MPInitParam.obtain().addComponentInitParam(mriverInitParam)
        
    }
}