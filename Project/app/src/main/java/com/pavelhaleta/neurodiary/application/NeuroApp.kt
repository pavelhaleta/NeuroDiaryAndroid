package com.pavelhaleta.neurodiary.application

import android.app.Application
import android.content.res.Resources

class NeuroApp: Application() {

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        mResources = resources
    }



    companion object{
        lateinit var mInstance: NeuroApp
        lateinit var mResources: Resources

        fun getInstance(): NeuroApp {
            return mInstance
        }

        fun getRes(): Resources {
            return mResources
        }
    }


}