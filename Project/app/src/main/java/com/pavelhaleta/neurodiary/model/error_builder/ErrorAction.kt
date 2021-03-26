package com.pavelhaleta.neurodiary.model.error_builder

import android.content.res.Resources
import com.pavelhaleta.neurodiary.R
import com.pavelhaleta.neurodiary.application.NeuroApp

enum class ErrorAction(var NAME: String) {
    SIGN_IN(NeuroApp.getRes().getString(R.string.ea_sign_in)),
    SIGN_UP(NeuroApp.getRes().getString(R.string.ea_sign_up));

    override fun toString(): String {
        return NAME
    }
}