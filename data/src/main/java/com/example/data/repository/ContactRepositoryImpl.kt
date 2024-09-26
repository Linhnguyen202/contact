package com.example.data.repository

import com.example.data.local.ContactDatabase
import com.example.data.mapper.ContactMapper
import com.example.data.utils.mapListFromDb
import com.example.domain.model.Contact
import com.example.domain.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContactRepositoryImpl @Inject constructor(
    private val contactMapper : ContactMapper,
    private val contactDatabase: ContactDatabase
) : ContactRepository {
    override suspend fun getContacts(): Flow<List<Contact>> {
        return mapListFromDb(contactDatabase.contactDao().getAllUser(), contactMapper)
    }

    override suspend fun addContact(query: Contact): Long {
        return contactDatabase.contactDao().insertUser(contactMapper.mapDataFromDomainToData(query))
    }

    override suspend fun removeContact(id: Int): Int {
       return contactDatabase.contactDao().deleteUser(id)
    }

    override suspend fun updateContact(query: Contact): Int {
        return contactDatabase.contactDao().updateUser(contactMapper.mapDataFromDomainToData(query))
    }


}