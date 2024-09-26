package com.example.data.di

import com.example.data.local.ContactDatabase
import com.example.data.mapper.ContactMapper
import com.example.data.repository.ContactRepositoryImpl
import com.example.domain.repository.ContactRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun bindContactRepository(
        userMapper: ContactMapper,
        contactDatabase: ContactDatabase
    ): ContactRepository {
        return ContactRepositoryImpl(userMapper,contactDatabase)
    }
}