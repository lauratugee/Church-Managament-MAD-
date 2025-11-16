package com.example.churchmanagementsystem.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.churchmanagementsystem.models.Group
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupDao {
    @Insert(onConflict= OnConflictStrategy.Companion.IGNORE)
    suspend fun insertGroup(group: Group)

    @Query("SELECT * FROM 'groups' ORDER BY name ASC")
        fun getAllGroups(): Flow<List<Group>>
}