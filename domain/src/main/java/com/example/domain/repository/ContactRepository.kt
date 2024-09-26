package com.example.domain.repository

import com.example.domain.model.Contact
import kotlinx.coroutines.flow.Flow

interface ContactRepository {
    suspend fun getContacts() : Flow<List<Contact>>

    suspend fun addContact(query : Contact) : Long

    suspend fun removeContact(id : Int) : Int

    suspend fun updateContact(query : Contact) : Int
}