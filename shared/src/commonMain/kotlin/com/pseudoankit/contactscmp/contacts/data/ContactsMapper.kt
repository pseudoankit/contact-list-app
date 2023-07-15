package com.pseudoankit.contactscmp.contacts.data

import com.pseudoankit.contactscmp.contacts.domain.Contact
import database.ContactEntity

suspend fun ContactEntity.mapToDomain() = Contact(
    id = id,
    firstName = firstName,
    lastName = lastName,
    email = email,
    phoneNumber = phoneNumber,
    photoBytes = null,
)