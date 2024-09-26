package com.example.contactapp.mapper

import com.example.contactapp.model.ContactModel
import com.example.contactapp.utils.UiLayerMapper
import com.example.domain.model.Contact
import javax.inject.Inject

class ContactMapper @Inject constructor() : UiLayerMapper<Contact, ContactModel> {
    override fun mapDataFromDomainToUI(type: Contact): ContactModel {
        return ContactModel(
            id = type.id,
            name = type.name,
            phone = type.phone,
            email = type.email,
            avatar = type.avatar
        )
    }
    override fun mapDataFromUIToDomain(type: ContactModel): Contact {
        return Contact(
            id = type.id,
            name = type.name,
            phone = type.phone,
            email = type.email,
            avatar = type.avatar
        )
    }


}