package com.pavelhaleta.neurodiary.ui.start.viewmodel

import androidx.appcompat.app.AppCompatActivity
import com.pavelhaleta.neurodiary.database.DBHelper
import com.pavelhaleta.neurodiary.database.entity.TableUser

class StartVM (var activity: AppCompatActivity, mVMListener: VMListener){

    fun isAccountExists(): Boolean {
        val dbHelper = DBHelper(activity)
        val list = TableUser.toList(dbHelper.db, "")
        dbHelper.close()
        return !list.isNullOrEmpty()
    }

    interface VMListener {
        fun enterUser()
        fun changeToSignUp()
        fun changeToSignIn()
    }
}