package com.pavelhaleta.neurodiary.model.other

import java.util.*

class MyDate() {

    var day: Int = 0
    var month: Int = 0
    var year: Int = 0

    constructor(dateString: String) : this() {
        day = dateString.substring(0,1).toInt()
        month = dateString.substring(3,4).toInt()
        year = dateString.substring(6,9).toInt()
    }

    fun current(){
        val calendar = Calendar.getInstance()
        day = calendar.get(Calendar.DAY_OF_MONTH)
        month = calendar.get(Calendar.MONTH)
        year = calendar.get(Calendar.YEAR)
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