package com.romanvytv.verbis.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.romanvytv.verbis.data.entities.TodayWord

@Dao
interface TodayWordDao {

    @Query("SELECT * FROM today_words")
    fun getAllWords(): List<TodayWord>

    @Query("SELECT * FROM today_words ORDER BY ID DESC LIMIT 1")
    fun getLastTodayWord(): TodayWord

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: TodayWord): Long
}