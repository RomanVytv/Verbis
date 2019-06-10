package com.romanvytv.verbis.data.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.romanvytv.verbis.MainApp
import com.romanvytv.verbis.data.entities.TodayWord
import com.romanvytv.verbis.data.entities.Word
import com.romanvytv.verbis.data.local.dao.TodayWordDao
import com.romanvytv.verbis.data.local.dao.WordDao

private const val DB_NAME = "words.db"

@Database(entities = [Word::class, TodayWord::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao
    abstract fun todayWordDao(): TodayWordDao

    companion object {
        fun newInstance() = Room.databaseBuilder(
            MainApp.appContext,
            AppDatabase::class.java,
            DB_NAME
        ).build()
    }
}