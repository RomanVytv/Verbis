package com.romanvytv.verbis.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "words")
data class Word(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,
    var word: String,
    var isFavorite: Boolean,
    var frequency: Double,
    @Ignore
    var results: List<Result>,
    @Embedded
    var pronunciation: Pronunciation
) {
    constructor() : this(
        null,
        word = "",
        isFavorite = false,
        frequency = 0.0,
        results = Collections.emptyList(),
        pronunciation = Pronunciation("")
    )

    data class Pronunciation(
        val all: String
    )

    companion object {
        fun empty() = Word()
    }
}