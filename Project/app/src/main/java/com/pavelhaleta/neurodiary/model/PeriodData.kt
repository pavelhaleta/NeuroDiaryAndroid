package com.pavelhaleta.neurodiary.model

import com.pavelhaleta.neurodiary.R
import com.pavelhaleta.neurodiary.application.NeuroApp

enum class PeriodData (var id: Int, var value: String){
    ONCE_DAY(1, NeuroApp.getRes().getString(R.string.period_data_once_day)),
    TWICE_DAY(2, NeuroApp.getRes().getString(R.string.period_data_twice_day)),
    ONCE_WEEK(3, NeuroApp.getRes().getString(R.string.period_data_once_week));
}