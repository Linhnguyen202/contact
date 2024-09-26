package com.example.domain.use_case

import com.example.domain.model.Contact
import com.example.domain.repository.ContactRepository
import com.example.domain.utils.LocalUseCaseParamsHasReturn
import javax.inject.Inject

class UpdateContactUseCase  @Inject constructor(
    private val contactRepository: ContactRepository
) : LocalUseCaseParamsHasReturn<Contact, Int> {
    override suspend fun execute(params: Contact): Int {
        return contactRepository.updateContact(params)
    }
}