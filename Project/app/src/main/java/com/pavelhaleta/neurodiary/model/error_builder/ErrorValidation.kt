package com.pavelhaleta.neurodiary.model.error_builder

import android.content.res.Resources
import com.pavelhaleta.neurodiary.R
import com.pavelhaleta.neurodiary.application.NeuroApp

enum class ErrorValidation(var NAME: String) {
    WRONG_DATA(NeuroApp.getRes().getString(R.string.ev_wrong_data)),
    DATA_IS_EMPTY(NeuroApp.getRes().getString(R.string.ev_empty_data)),
    STRING_TOO_LONG(NeuroApp.getRes().getString(R.string.ev_data_too_long));

    override fun toString(): String {
        return NAME
    }
}