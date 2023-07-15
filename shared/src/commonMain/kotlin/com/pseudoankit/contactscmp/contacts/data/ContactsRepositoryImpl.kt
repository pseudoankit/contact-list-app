package com.pseudoankit.contactscmp.contacts.data

import com.pseudoankit.contactscmp.contacts.domain.Contact
import com.pseudoankit.contactscmp.contacts.domain.ContactsRepository
import com.pseudoankit.contactscmp.core.data.ImageStorage
import com.pseudoankit.contactscmp.database.ContactDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.supervisorScope
import kotlinx.datetime.Clock

class ContactsRepositoryImpl(
    private val database: ContactDatabase,
    private val imageStorage: ImageStorage
) : ContactsRepository {

    private val query by lazy { database.contactQueries }

    override fun getContacts(): Flow<List<Contact>> {
        return query
            .getContacts()
            .asFlow()
            .mapToList()
            .map { entities ->
                supervisorScope {
                    entities.map { entity ->
                        async { entity.mapToDomain(imageStorage) }
                    }.awaitAll()
                }
            }
    }

    override fun getRecentContacts(limit: Long): Flow<List<Contact>> {
        return query
            .getContactsByLimit(limit)
            .asFlow()
            .mapToList()
            .map { entities ->
                supervisorScope {
                    entities.map { entity ->
                        async { entity.mapToDomain(imageStorage) }
                    }.awaitAll()
                }
            }
    }

    override fun getContactById(id: Long): Flow<Contact> {
        return query
            .getContactById(id)
            .asFlow()
            .mapToOne()
            .map { entity ->
                entity.mapToDomain(imageStorage)
            }
    }

    override suspend fun insert(contact: Contact) = with(contact) {
        query.insert(
            id = id,
            firstName = firstName,
            lastName = lastName,
            phoneNumber = phoneNumber,
            email = email,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            imagePath = contact.photoBytes?.let {
                imageStorage.saveImage(it)
            }
        )
    }

    override suspend fun delete(id: Long) {
        val entity = query.getContactById(id).executeAsOne()
        entity.imagePath?.let {
            imageStorage.deleteImage(it)
        }

        query.deleteById(id)
    }
}