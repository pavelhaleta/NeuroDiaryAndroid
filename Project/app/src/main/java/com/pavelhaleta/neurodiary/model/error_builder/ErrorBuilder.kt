package com.pavelhaleta.neurodiary.model.error_builder

import android.content.res.Resources
import com.pavelhaleta.neurodiary.R
import com.pavelhaleta.neurodiary.application.NeuroApp

class ErrorBuilder {
    companion object{
        fun default(): String {
            return NeuroApp.getRes().getString(R.string.error_default)
        }

        fun default(data: ErrorObject): String {
            return NeuroApp.getRes().getString(R.string.error_object, data.toString())
        }

        fun default(data: ErrorObject, action: ErrorAction): String {
            return NeuroApp.getRes().getString(R.string.error_object_action, action.toString(), data.toString())
        }

        fun default(data: ErrorObject, validation: ErrorValidation): String {
            return NeuroApp.getRes().getString(R.string.error_validation, validation.toString(), data.toString())
        }
    }

}