package com.romanvytv.verbis.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "today_words")
data class TodayWord(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,
    var word: String,
    var timeStamp: Long
) {
    constructor() : this(null, "", System.currentTimeMillis())
    constructor(word: String) : this(null, word, System.currentTimeMillis())

    companion object {
        fun empty() = TodayWord()
    }
}