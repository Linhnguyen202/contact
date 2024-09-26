package com.example.domain.use_case

import com.example.domain.model.Contact
import com.example.domain.repository.ContactRepository
import com.example.domain.utils.LocalUseCaseParamsHasReturn
import javax.inject.Inject

class AddContactUseCase @Inject constructor(
    private val contactRepository: ContactRepository
) : LocalUseCaseParamsHasReturn<Contact, Long> {
    override suspend fun execute(params: Contact): Long {
      return contactRepository.addContact(params)
    }
}