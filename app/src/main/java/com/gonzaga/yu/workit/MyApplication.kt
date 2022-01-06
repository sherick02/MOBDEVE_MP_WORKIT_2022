package com.gonzaga.yu.workit

import android.app.Application
import android.content.Context

class MyApplication : Application() {

    companion object {
        var instance: MyApplication? = null
            private set

        @JvmStatic
        val appContext: Context?
            get() {
                if (instance == null) {
                    instance = MyApplication()
                }
                return instance
            }
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        //TypefaceUtil.overrideFont(applicationContext, "SERIF", "fonts/Poppins-Medium.ttf")
        instance = this
    }

    override fun onTerminate() {
        super.onTerminate()
    }


}