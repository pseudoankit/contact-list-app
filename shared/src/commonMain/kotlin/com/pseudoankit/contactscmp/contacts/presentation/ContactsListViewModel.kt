package com.pseudoankit.contactscmp.contacts.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.pseudoankit.contactscmp.contacts.domain.Contact
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ContactsListViewModel : ViewModel() {

    private val _state = MutableStateFlow(ContactsListState(initialContacts))
    val state = _state.asStateFlow()

    var newContact: Contact? by mutableStateOf(null)
        private set

    fun onEvent(event: ContactListEvent) {

    }
}

private val initialContacts = List(50) {
    Contact(
        id = it.toLong(),
        firstName = "first",
        lastName = "last $it",
        email = "email$it@gmail.com",
        phoneNumber = "123456789$it",
        photoBytes = null
    )
}