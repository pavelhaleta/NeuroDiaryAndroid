package com.pavelhaleta.neurodiary.model.error_builder

import android.content.res.Resources
import com.pavelhaleta.neurodiary.R
import com.pavelhaleta.neurodiary.application.NeuroApp

enum class ErrorObject(var NAME: String) {
    ACCOUNT_NAME(NeuroApp.getRes().getString(R.string.eo_account_name)),
    ACCOUNT(NeuroApp.getRes().getString(R.string.eo_account)),
    NAME_DEFAULT(NeuroApp.getRes().getString(R.string.eo_name)),
    ACCOUNT_PASSWORD(NeuroApp.getRes().getString(R.string.eo_password));

    override fun toString(): String {
        return NAME
    }
}