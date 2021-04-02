package com.pavelhaleta.neurodiary.ui.start.viewmodel

import android.app.Activity
import com.pavelhaleta.neurodiary.application.NeuroApp
import com.pavelhaleta.neurodiary.database.DBHelper
import com.pavelhaleta.neurodiary.model.error_builder.ErrorBuilder
import com.pavelhaleta.neurodiary.model.error_builder.ErrorObject
import com.pavelhaleta.neurodiary.model.error_builder.ErrorValidation

class SignInVM(
    var activity: Activity,
    var mVMListener: VMListener,
    var mStartVMListener: StartVM.VMListener
) {


    fun signIn(password: String) {
        val dbHelper = DBHelper(activity)
        val loadResult = NeuroApp.getInstance().user.load(dbHelper.db, password)
        dbHelper.close()
        if (loadResult) {
            mStartVMListener.enterUser()
        } else {
            mVMListener.errorPassword(
                ErrorBuilder.default(
                    ErrorObject.ACCOUNT_PASSWORD,
                    ErrorValidation.WRONG_DATA
                )
            )
        }
    }

    fun changeToSignUp() {
        mStartVMListener.changeToSignUp()
    }

    interface VMListener {
        fun errorPassword(error: String)
    }
}

