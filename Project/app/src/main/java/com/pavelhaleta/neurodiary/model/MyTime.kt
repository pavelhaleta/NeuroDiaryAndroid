package com.pavelhaleta.neurodiary.model

class MyTime() {

    var second: Int = 0
    var minute: Int = 0
    var hour: Int = 0

    constructor(timeString: String): this(){
        
    }

    override fun toString(): String {
        val hours = if (this.hour < 10) {
            "0${hour}"
        } else {
            "$hour"
        }
        val minutes = if(minute <  10){
            "0${minute}"
        }else{
            "$minute"
        }
        val seconds = if(second <  10){
            "0${second}"
        }else{
            "$second"
        }
        return "$hours:$minutes:$seconds"
    }
}