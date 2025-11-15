package com.example.churchmanagementsystem.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.churchmanagementsystem.models.Group

@Database(entities = [Group::class], version = 1,exportSchema=false)
abstract class AppDatabase:RoomDatabase() {
    abstract fun groupDao(): GroupDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "church_management_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }

        }
    }


