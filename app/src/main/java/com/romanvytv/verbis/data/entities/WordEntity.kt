package com.romanvytv.verbis.data.entities

import com.google.gson.annotations.SerializedName
import java.util.*


data class WordEntity(
    @SerializedName("word") val word: String,
    @SerializedName("frequency") val frequency: Double,
    @SerializedName("results") val results: List<Result>,
    @SerializedName("syllables") val syllables: Syllables,
    @SerializedName("pronunciation") val pronunciation: Pronunciation
) {
    data class Pronunciation(
        @SerializedName("all") val all: String
    )

    data class Result(
        @SerializedName("definition") val definition: String,
        @SerializedName("partOfSpeech") val partOfSpeech: String,
        @SerializedName("typeOf") val typeOf: List<String>,
        @SerializedName("derivation") val derivation: List<String>
    )

    data class Syllables(
        @SerializedName("count") val count: Int,
        @SerializedName("list") val list: List<String>
    )

    companion object {
        fun empty() = WordEntity(
            word = "",
            frequency = 0.0,
            results = Collections.emptyList(),
            syllables = Syllables(0, Collections.emptyList()),
            pronunciation = Pronunciation("")
        )
    }
}