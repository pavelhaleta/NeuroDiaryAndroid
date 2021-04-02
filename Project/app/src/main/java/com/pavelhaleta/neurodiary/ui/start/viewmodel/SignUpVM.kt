package com.pavelhaleta.neurodiary.ui.start.viewmodel

import android.app.Activity
import com.pavelhaleta.neurodiary.application.NeuroApp
import com.pavelhaleta.neurodiary.database.DBHelper
import com.pavelhaleta.neurodiary.model.error_builder.ErrorBuilder
import com.pavelhaleta.neurodiary.model.error_builder.ErrorObject
import com.pavelhaleta.neurodiary.model.error_builder.ErrorValidation

class SignUpVM(
    var activity: Activity,
    var mVMListener: VMListener,
    var mStartVMListener: StartVM.VMListener
) {

    private var password: String = ""
    private var name: String = ""

    fun changeToSignIn() {
        mStartVMListener.changeToSignUp()
    }

    fun createAccount() {
        val dbHelper = DBHelper(activity)
        val result = NeuroApp.getInstance().user.create(dbHelper.db, name, password)
        dbHelper.close()
        if (result) {
            mStartVMListener.enterUser()
        }
    }

    fun setPassword(passwordEntered: String) {
        if (validatePassword(passwordEntered)) {
            password = passwordEntered
            if (name.isNotEmpty()) {
                mVMListener.allowCreate(true)
            } else {
                mVMListener.allowCreate(false)
            }
        }
    }

    private fun validatePassword(password: String): Boolean {
        if (password.isEmpty()) {
            mVMListener.errorPassword(
                ErrorBuilder.default(
                    ErrorObject.ACCOUNT_PASSWORD,
                    ErrorValidation.DATA_IS_EMPTY
                )
            )
            return false
        }
        if (password.length > 50) {
            mVMListener.errorPassword(
                ErrorBuilder.default(
                    ErrorObject.ACCOUNT_PASSWORD,
                    ErrorValidation.STRING_TOO_LONG
                )
            )
            return false
        }
        mVMListener.errorPassword(null)
        return true
    }

    fun setName(nameEntered: String) {
        if (validateName(nameEntered)) {
            name = nameEntered
            if (password.isNotEmpty()) {
                mVMListener.allowCreate(true)
            } else {
                mVMListener.allowCreate(false)
            }
        }
    }

    private fun validateName(name: String): Boolean {
        if (name.isEmpty()) {
            mVMListener.errorName(
                ErrorBuilder.default(
                    ErrorObject.ACCOUNT_NAME,
                    ErrorValidation.DATA_IS_EMPTY
                )
            )
            return false
        }
        if (name.length > 50) {
            mVMListener.errorName(
                ErrorBuilder.default(
                    ErrorObject.ACCOUNT_NAME,
                    ErrorValidation.STRING_TOO_LONG
                )
            )
            return false
        }
        mVMListener.errorName(null)
        return true
    }

    interface VMListener {
        fun allowCreate(allow: Boolean)
        fun errorPassword(error: String?)
        fun errorName(error: String?)
    }

}

