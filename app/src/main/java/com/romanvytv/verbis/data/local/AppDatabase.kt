package com.romanvytv.verbis.data.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.romanvytv.verbis.MainApp
import com.romanvytv.verbis.data.entities.TodayWord
import com.romanvytv.verbis.data.entities.Word
import com.romanvytv.verbis.data.local.dao.TodayWordDao
import com.romanvytv.verbis.data.local.dao.WordDao

@Database(entities = [Word::class, TodayWord::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao
    abstract fun todayWordDao(): TodayWordDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        MainApp.appContext,
                        AppDatabase::class.java, "words.db"
                    ).build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}