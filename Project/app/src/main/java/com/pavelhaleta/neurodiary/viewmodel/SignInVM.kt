package com.pavelhaleta.neurodiary.viewmodel

import android.app.Activity
import com.pavelhaleta.neurodiary.database.DBHelper
import com.pavelhaleta.neurodiary.database.tables.User
import com.pavelhaleta.neurodiary.viewmodel.listeners.SignInVMListener
import com.pavelhaleta.neurodiary.viewmodel.listeners.StartActivityVMListener
import com.pavelhaleta.neurodiary.viewmodel.other.ContactMessage

class SignInVM(var activity: Activity, var mSignInVMListener: SignInVMListener,var mStartActivityVMListener: StartActivityVMListener) {

    private var passwordSize: Int = 0
    private var userList = ArrayList<User>()

    init {
        val dbHelper = DBHelper(activity)
        userList = User.toList(dbHelper.db, "")!!
        passwordSize = userList.minOf { it.password.length }
        dbHelper.close()
    }

    fun signIn(password: String){
        if (validatePassword(password)){
            mStartActivityVMListener.enterUser()
        }else{
            mSignInVMListener.errorPassword(ContactMessage.PASSWORD_WRONG)
        }
    }


    private fun validatePassword(enteredPassword: String): Boolean {
        return if (enteredPassword.length < passwordSize){
            false
        }else{
            userList.firstOrNull { it.password == enteredPassword } != null
        }
    }
}
