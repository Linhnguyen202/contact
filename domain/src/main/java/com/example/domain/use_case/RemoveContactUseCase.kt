package com.example.domain.use_case

import com.example.domain.repository.ContactRepository
import com.example.domain.utils.LocalUseCaseParamsHasReturn
import javax.inject.Inject

class RemoveContactUseCase @Inject constructor(
    private val contactRepository: ContactRepository
) : LocalUseCaseParamsHasReturn<Int, Int> {
    override suspend fun execute(params: Int): Int {
       return contactRepository.removeContact(params)
    }

}