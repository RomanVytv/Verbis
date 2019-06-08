package com.romanvytv.verbis.data.entities

import java.util.*


//TODO: save long instead of Date
data class TodayWordEntity(val word : String, val date: Date) {

    companion object {
        fun empty() = TodayWordEntity(word = "", date = Date())
    }
}