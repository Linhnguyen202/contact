package com.example.data.mapper

import com.example.data.model.ContactDTO
import com.example.data.utils.DataLayerMapper
import com.example.domain.model.Contact
import javax.inject.Inject

class ContactMapper @Inject constructor() : DataLayerMapper<Contact, ContactDTO> {
    override fun mapDataFromDomainToData(type: Contact): ContactDTO {
        return ContactDTO(
            id = type.id,
            name = type.name,
            phone = type.phone,
            email = type.email,
            avatar = type.avatar
        )
    }

    override fun mapDataFromDataToDomain(type: ContactDTO): Contact {
        return Contact(
            id = type.id,
            name = type.name,
            phone = type.phone,
            email = type.email,
            avatar = type.avatar
        )
    }

}