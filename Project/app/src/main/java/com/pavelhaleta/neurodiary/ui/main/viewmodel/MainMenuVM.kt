package com.pavelhaleta.neurodiary.ui.main.viewmodel

import android.app.Activity
import com.pavelhaleta.neurodiary.model.Record
import com.pavelhaleta.neurodiary.model.other.MyDate

class MainMenuVM(var activity: Activity, var mVMListener: VMListener) {

    private var record: Record? = null


    fun loadByDate(date: MyDate) {

    }

    fun nextDay() {

    }

    fun prevDay() {

    }

    fun editText() {

    }

    fun addImage() {

    }

    fun onStart(){

    }


    interface VMListener {
        fun refreshRecord() {}
        fun openNeuroMenu() {}
        fun editRecord() {}
    }
}

