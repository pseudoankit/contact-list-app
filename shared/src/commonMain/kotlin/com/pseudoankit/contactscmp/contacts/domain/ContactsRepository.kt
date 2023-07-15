package com.pseudoankit.contactscmp.contacts.domain

import kotlinx.coroutines.flow.Flow

interface ContactsRepository {
    fun getContacts(): Flow<List<Contact>>
    fun getRecentContacts(limit: Long): Flow<List<Contact>>
    fun getContactById(id: Long): Flow<Contact>
    suspend fun insert(contact: Contact)
    suspend fun delete(id: Long)
}