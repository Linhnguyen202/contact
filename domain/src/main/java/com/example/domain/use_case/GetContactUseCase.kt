package com.example.domain.use_case

import com.example.domain.model.Contact
import com.example.domain.repository.ContactRepository
import com.example.domain.utils.LocalUseCaseNonParam
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetContactUseCase @Inject constructor(
    private val contactRepository: ContactRepository
) : LocalUseCaseNonParam<Flow<List<Contact>>> {
    override suspend fun execute(): Flow<List<Contact>> {
       return contactRepository.getContacts()
    }

}