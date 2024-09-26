package com.example.data.di

import com.example.data.local.ContactDatabase
import com.example.data.local.dao.ContactDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {
    @Provides
    fun providesUser(
        database: ContactDatabase,
    ): ContactDao = database.contactDao()
}