package com.pseudoankit.contactscmp.contacts.data

import com.pseudoankit.contactscmp.contacts.domain.Contact
import com.pseudoankit.contactscmp.contacts.domain.ContactsRepository
import com.pseudoankit.contactscmp.database.ContactDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock

class ContactsRepositoryImpl(
    private val database: ContactDatabase
) : ContactsRepository {

    private val query by lazy { database.contactQueries }

    override fun getContacts(): Flow<List<Contact>> {
        return query
            .getContacts()
            .asFlow()
            .mapToList()
            .map { entities ->
                entities.map { entity -> entity.mapToDomain() }
            }
    }

    override fun getRecentContacts(limit: Long): Flow<List<Contact>> {
        return query
            .getContactsByLimit(limit)
            .asFlow()
            .mapToList()
            .map { entities ->
                entities.map { entity -> entity.mapToDomain() }
            }
    }

    override fun getContactsById(id: Long): Flow<Contact> {
        return query
            .getContactById(id)
            .asFlow()
            .mapToOne()
            .map { entity ->
                entity.mapToDomain()
            }
    }

    override fun insert(contact: Contact) = with(contact) {
        query.insert(
            id = id,
            firstName = firstName,
            lastName = lastName,
            phoneNumber = phoneNumber,
            email = email,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            imagePath = null
        )
    }

    override fun delete(id: Long) {
        query.deleteById(id)
    }
}