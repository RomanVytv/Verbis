package com.romanvytv.verbis.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "words_results")
data class Result(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,
    var wordId: Long,
    var definition: String,
    var partOfSpeech: String,
    var typeOf: List<String>,
    var derivation: List<String>
) {

    constructor() : this(
        id = null,
        wordId = 0L,
        definition = "",
        partOfSpeech = "",
        typeOf = Collections.emptyList(),
        derivation = Collections.emptyList()
    )

    companion object {
        fun empty() = Result()
    }
}