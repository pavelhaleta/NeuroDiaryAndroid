package com.pavelhaleta.neurodiary.application

import android.app.Application
import android.content.res.Resources
import com.pavelhaleta.neurodiary.model.User
import com.pavelhaleta.neurodiary.model.other.UserMode

class NeuroApp: Application() {

    var userMode = UserMode.USER_MODE
    var user = User()

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