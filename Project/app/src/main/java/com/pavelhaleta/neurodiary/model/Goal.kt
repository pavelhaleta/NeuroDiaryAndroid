package com.pavelhaleta.neurodiary.model

import com.pavelhaleta.neurodiary.R
import com.pavelhaleta.neurodiary.application.NeuroApp

enum class Goal (var id: Int, var value: String){
    DEFAULT(1, NeuroApp.getRes().getString(R.string.goal_default)),
    PSYCHOLOGY(2, NeuroApp.getRes().getString(R.string.goal_psychology));
}