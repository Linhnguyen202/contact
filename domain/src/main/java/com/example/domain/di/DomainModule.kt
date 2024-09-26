package com.example.domain.di

import com.example.domain.model.Contact
import com.example.domain.repository.ContactRepository
import com.example.domain.use_case.AddContactUseCase
import com.example.domain.use_case.GetContactUseCase
import com.example.domain.use_case.RemoveContactUseCase
import com.example.domain.utils.LocalUseCaseNonParam
import com.example.domain.utils.LocalUseCaseParamsHasReturn
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DomainModule {
    @Provides
    @Singleton
    fun provideAddContactUseCase(contactRepository: ContactRepository): LocalUseCaseParamsHasReturn<Contact,Long> {
        return AddContactUseCase(contactRepository)
    }

    @Provides
    @Singleton
    fun provideRemoveContactUseCase(contactRepository: ContactRepository): LocalUseCaseParamsHasReturn<Int, Int>{
        return RemoveContactUseCase(contactRepository)
    }

    @Provides
    @Singleton
    fun provideGetContactUseCase(contactRepository: ContactRepository): LocalUseCaseNonParam<Flow<List<Contact>>> {
        return GetContactUseCase(contactRepository)
    }

}