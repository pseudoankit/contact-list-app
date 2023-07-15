package com.pseudoankit.contactscmp.di

import com.pseudoankit.contactscmp.contacts.data.ContactsRepositoryImpl
import com.pseudoankit.contactscmp.contacts.domain.ContactsRepository
import com.pseudoankit.contactscmp.core.data.DatabaseDriverFactory
import com.pseudoankit.contactscmp.database.ContactDatabase

actual class AppModule {

    actual val contactsRepository: ContactsRepository by lazy {
        ContactsRepositoryImpl(
            database = ContactDatabase(
                DatabaseDriverFactory().create()
            )
        )
    }
}