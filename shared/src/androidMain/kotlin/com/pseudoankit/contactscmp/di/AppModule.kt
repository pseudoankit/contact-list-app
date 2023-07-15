package com.pseudoankit.contactscmp.di

import android.content.Context
import com.pseudoankit.contactscmp.contacts.data.ContactsRepositoryImpl
import com.pseudoankit.contactscmp.contacts.domain.ContactsRepository
import com.pseudoankit.contactscmp.core.data.DatabaseDriverFactory
import com.pseudoankit.contactscmp.core.data.ImageStorage
import com.pseudoankit.contactscmp.database.ContactDatabase

actual class AppModule(
    private val context: Context
) {

    actual val contactsRepository: ContactsRepository by lazy {
        ContactsRepositoryImpl(
            database = ContactDatabase(
                DatabaseDriverFactory(context).create()
            ),
            imageStorage = ImageStorage(context = context)
        )
    }
}