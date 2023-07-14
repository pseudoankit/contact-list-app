package com.pseudoankit.contactscmp.contacts.presentation

import com.pseudoankit.contactscmp.contacts.domain.Contact

data class ContactsListState(
    val contacts: List<Contact> = emptyList(),
    val recentlyAddedContacts: List<Contact> = emptyList(),
    val selectedContact: Contact? = null,
    val isAddContactSheetOpen: Boolean = false,
    val isSelectedContactSheetOpen: Boolean = false,
    val firstNameError: String? = null,
    val lastNameError: String? = null,
    val emailError: String? = null,
    val phoneNumberError: String? = null,
)

sealed interface ContactListEvent {
    object OnAddNewContactClick : ContactListEvent
    object DismissContact : ContactListEvent
    data class OnFirstNameChanged(val value: String) : ContactListEvent
    data class OnLastNameChanged(val value: String) : ContactListEvent
    data class OnEmailChanged(val value: String) : ContactListEvent
    data class OnPhoneNumberChanged(val value: String) : ContactListEvent
    class OnPhotoPicked(val bytes: ByteArray) : ContactListEvent
    object OnAddPhotoClicked : ContactListEvent
    object SaveContact : ContactListEvent
    data class SelectContact(val contact: Contact) : ContactListEvent
    data class EditContact(val contact: Contact) : ContactListEvent
    object DeleteContact : ContactListEvent
}
