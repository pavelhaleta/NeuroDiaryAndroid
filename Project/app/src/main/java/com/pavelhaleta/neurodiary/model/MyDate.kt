package com.pavelhaleta.neurodiary.model

import java.util.*

class MyDate() {

    var day: Int = 0
    var month: Int = 0
    var year: Int = 0

    constructor(dateString: String) : this() {

    }

    constructor(date: Date) : this() {

    }

    override fun toString(): String {
        val days = if (this.day < 10) {
            "0${day}"
        } else {
            "$day"
        }
        val months = if(month <  10){
            "0${month}"
        }else{
            "$month"
        }
        return "$days.$months.$year"
    }

    fun addDays(days: Int) {

    }

    fun addMonths(months: Int) {

    }

    fun addYears(years: Int) {

    }
}