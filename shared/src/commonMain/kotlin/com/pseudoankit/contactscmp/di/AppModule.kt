package com.pseudoankit.contactscmp.di

import com.pseudoankit.contactscmp.contacts.domain.ContactsRepository

expect class AppModule {

    val contactsRepository: ContactsRepository
}