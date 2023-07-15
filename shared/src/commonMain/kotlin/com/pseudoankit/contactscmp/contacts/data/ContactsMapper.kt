package com.pseudoankit.contactscmp.contacts.data

import com.pseudoankit.contactscmp.contacts.domain.Contact
import com.pseudoankit.contactscmp.core.data.ImageStorage
import database.ContactEntity

suspend fun ContactEntity.mapToDomain(imageStorage: ImageStorage) = Contact(
    id = id,
    firstName = firstName,
    lastName = lastName,
    email = email,
    phoneNumber = phoneNumber,
    photoBytes = imagePath?.let { imageStorage.getImage(it) },
)
