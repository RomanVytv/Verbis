package com.romanvytv.verbis.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "today_words")
data class TodayWord(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,
    val word: String,
    val timeStamp: Long
) {
    constructor(word: String) : this(null, word, System.currentTimeMillis())

    companion object {
        fun empty() = TodayWord(id = null, word = "", timeStamp = System.currentTimeMillis())
    }
}