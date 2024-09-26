package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.dao.ContactDao
import com.example.data.model.ContactDTO

@Database(entities = [ContactDTO::class], version = 1, exportSchema = false)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao() : ContactDao
}