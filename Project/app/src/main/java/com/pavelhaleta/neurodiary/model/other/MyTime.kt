package com.pavelhaleta.neurodiary.model.other

import java.util.*

class MyTime() {

    var second: Int = 0
    var minute: Int = 0
    var hour: Int = 0

    constructor(timeString: String) : this() {
        hour = timeString.substring(0, 1).toInt()
        minute = timeString.substring(3, 4).toInt()
        second = timeString.substring(6, 7).toInt()
    }

    fun current() {
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        hour = calendar.get(Calendar.HOUR)
        minute = calendar.get(Calendar.MINUTE)
        second = calendar.get(Calendar.SECOND)
    }


    override fun toString(): String {
        val hours = if (this.hour < 10) {
            "0${hour}"
        } else {
            "$hour"
        }
        val minutes = if (minute < 10) {
            "0${minute}"
        } else {
            "$minute"
        }
        val seconds = if (second < 10) {
            "0${second}"
        } else {
            "$second"
        }
        return "$hours:$minutes:$seconds"
    }
}