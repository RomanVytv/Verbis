package com.romanvytv.verbis.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.romanvytv.verbis.data.entities.Result


@Dao
interface ResultDao {

    @Query("SELECT * FROM words_results WHERE wordId LIKE :wordId")
    fun getResultByWordId(wordId : Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(result: Result) : Long
}