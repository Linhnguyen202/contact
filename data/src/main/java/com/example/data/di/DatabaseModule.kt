package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.ContactDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesContactDatabase(
        @ApplicationContext context: Context,
    ): ContactDatabase = Room.databaseBuilder(
        context,
        ContactDatabase::class.java,
        "contact_database",
    ).build()
}