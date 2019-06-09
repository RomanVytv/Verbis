package com.romanvytv.verbis.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "words")
data class Word(
    @PrimaryKey(autoGenerate = true)
    var id : Long?,
    val word: String,
    val isFavorite : Boolean,
    val frequency: Double,
    val results: List<Result>,
    @Embedded
    val pronunciation: Pronunciation
) {
    data class Pronunciation(
        val all: String
    )

    data class Result(
        val definition: String,
        val partOfSpeech: String,
        val typeOf: List<String>,
        val derivation: List<String>
    )

    companion object {
        fun empty() = Word(
            null,
            word = "",
            isFavorite = false,
            frequency = 0.0,
            results = Collections.emptyList(),
            pronunciation = Pronunciation("")
        )
    }
}