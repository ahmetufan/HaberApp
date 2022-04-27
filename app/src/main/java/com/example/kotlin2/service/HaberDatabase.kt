package com.example.kotlin2.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlin2.Haber
import com.example.kotlin2.HaberResult

@Database(entities = [Haber::class], version = 1)
abstract class HaberDatabase : RoomDatabase() {

    abstract fun haberDao(): HaberDao


    companion object {

        @Volatile
        private var instance: HaberDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {

            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, HaberDatabase::class.java, "haberdatabase").build()

    }
}