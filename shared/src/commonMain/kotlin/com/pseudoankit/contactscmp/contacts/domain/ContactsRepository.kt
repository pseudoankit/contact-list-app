package com.pseudoankit.contactscmp.contacts.domain

import kotlinx.coroutines.flow.Flow

interface ContactsRepository {
    fun getContacts(): Flow<List<Contact>>
    fun getRecentContacts(limit: Long): Flow<List<Contact>>
    fun getContactsById(id: Long): Flow<Contact>
    fun insert(contact: Contact)
    fun delete(id: Long)
}