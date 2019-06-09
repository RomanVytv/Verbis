package com.romanvytv.verbis.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.romanvytv.verbis.data.entities.Word

@Dao
interface WordDao {

    @Query("SELECT * FROM words")
    fun getAllWords() : List<Word>

    @Query("SELECT * FROM words WHERE words.isFavorite IS 1")
    fun getFavoriteWords() : List<Word>

    @Insert(onConflict = REPLACE)
    fun insert(word: Word) : Long
}