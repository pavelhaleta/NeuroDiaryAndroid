package com.pavelhaleta.neurodiary.viewmodel

import android.app.Activity
import com.pavelhaleta.neurodiary.database.DBHelper
import com.pavelhaleta.neurodiary.database.tables.Action
import com.pavelhaleta.neurodiary.database.tables.User
import com.pavelhaleta.neurodiary.viewmodel.listeners.SignUpVMListener
import com.pavelhaleta.neurodiary.viewmodel.listeners.StartActivityVMListener
import com.pavelhaleta.neurodiary.viewmodel.other.ContactMessage

class SignUpVM(var activity: Activity, var mSignUpVMListener: SignUpVMListener, var mStartActivityVMListener: StartActivityVMListener): UserActionWritter {

    private var name: String? = null
    private var password: String? = null
    private var userList : ArrayList<User>? = null

    init {
        val dbHelper = DBHelper(activity)
        userList = User.toList(dbHelper.db, "")
        dbHelper.close()
    }

    fun writeAction(user: User, action: Action){
        val dbHelper = DBHelper(activity)
        writeActionToDB(dbHelper.db, user, action)
        dbHelper.close()
    }

    fun createAccount(){
        val user = User()
        user.name = this.name!!
        user.password = this.password!!
        val dbHelper = DBHelper(activity)
        user.save(dbHelper.db)
        dbHelper.close()
        mStartActivityVMListener.enterUser()
    }

    fun setPassword(passwordEntered: String){
        if(validatePassword(passwordEntered)){
            password = passwordEntered
            if (name != null){
                mSignUpVMListener.allowCreate(true)
            }else{
                mSignUpVMListener.allowCreate(false)
            }
        }
    }
    private fun validatePassword(password: String): Boolean{
        if (password.isEmpty()){
            mSignUpVMListener.errorPassword(ContactMessage.PASSWORD_EMPTY)
            return false
        }
        if (password.length > 50){
            mSignUpVMListener.errorPassword(ContactMessage.PASSWORD_LENGTH)
            return false
        }
        mSignUpVMListener.errorPassword(null)
        return true
    }
    fun setName(nameEntered: String){
        if(validateName(nameEntered)){
            name = nameEntered
            if (password != null){
                mSignUpVMListener.allowCreate(true)
            }else{
                mSignUpVMListener.allowCreate(false)
            }
        }
    }
    private fun validateName(name: String): Boolean{
        if (name.isEmpty()){
            mSignUpVMListener.errorName(ContactMessage.ACCOUNT_NAME_EMPTY)
            return false
        }
        if (name.length > 50){
            mSignUpVMListener.errorName(ContactMessage.ACCOUNT_NAME_LENGTH)
            return false
        }
        mSignUpVMListener.errorName(null)
        return true
    }

}
