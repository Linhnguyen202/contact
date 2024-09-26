package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

import com.example.data.model.ContactDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user : ContactDTO) : Long

    @Query("SELECT * FROM ContactDTO  ORDER BY name ASC")
    fun getAllUser(): Flow<List<ContactDTO>>

    @Query("DELETE FROM ContactDTO WHERE id = :itemId")
    suspend fun deleteUser(itemId: Int) : Int

    @Update
    suspend fun updateUser(user: ContactDTO) : Int


}