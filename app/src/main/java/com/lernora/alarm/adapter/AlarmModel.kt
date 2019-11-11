package com.lernora.alarm.adapter

class AlarmModel {

    var alarm_time: String? = null
    var status: Int = 0

    fun getTime(): String {
        return alarm_time.toString()
    }

    fun setTime(name: String) {
        this.alarm_time = name
    }
}