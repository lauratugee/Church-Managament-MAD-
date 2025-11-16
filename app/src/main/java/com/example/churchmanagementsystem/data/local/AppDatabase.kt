package com.example.churchmanagementsystem.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.churchmanagementsystem.models.Group
import com.example.churchmanagementsystem.models.MassSchedule



@Database(
    entities = [
        Group::class,
        MassSchedule::class
    ],
    version = 2,
    exportSchema = false
)


abstract class AppDatabase:RoomDatabase() {
    abstract fun groupDao(): GroupDao
    abstract fun massScheduleDao(): MassScheduleDao


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


