package com.pseudoankit.contactscmp.contacts.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PersonAdd
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pseudoankit.contactscmp.contacts.domain.Contact
import com.pseudoankit.contactscmp.contacts.domain.ContactValidator
import com.pseudoankit.contactscmp.contacts.presentation.ContactListEvent
import com.pseudoankit.contactscmp.contacts.presentation.ContactsListState
import com.pseudoankit.contactscmp.contacts.presentation.ContactsListViewModel
import com.pseudoankit.contactscmp.contacts.presentation.ui.component.AddContactSheet
import com.pseudoankit.contactscmp.contacts.presentation.ui.component.ContactListItem
import com.pseudoankit.contactscmp.di.AppModule
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory

@Composable
fun ContactListScreen(
    appModule: AppModule
) {
    val viewModel = getViewModel(
        key = "contact-list-screen",
        factory = viewModelFactory {
            ContactsListViewModel(
                contactsRepository = appModule.contactsRepository,
                contactValidator = ContactValidator
            )
        }
    )

    val state by viewModel.state.collectAsState()

    ContactListScreen(
        state = state,
        newContact = viewModel.newContact,
        onEvent = viewModel::onEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ContactListScreen(
    state: ContactsListState,
    newContact: Contact?,
    onEvent: (ContactListEvent) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(ContactListEvent.OnAddNewContactClick)
                },
                shape = RoundedCornerShape(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.PersonAdd,
                    contentDescription = "Add contact"
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
//            item {
//                RecentlyAddedContacts(
//                    contacts = state.recentlyAddedContacts,
//                    onClick = {
//                        onEvent(ContactListEvent.SelectContact(it))
//                    }
//                )
//            }

            item {
                Text(
                    text = "My contacts (${state.contacts.size})",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    fontWeight = FontWeight.Bold
                )
            }

            items(state.contacts) { contact ->
                ContactListItem(
                    contact = contact,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onEvent(ContactListEvent.SelectContact(contact))
                        }
                        .padding(horizontal = 16.dp)
                )
            }
        }
    }

//    ContactDetailSheet(
//        isOpen = state.isSelectedContactSheetOpen,
//        selectedContact = state.selectedContact,
//        onEvent = onEvent,
//    )
    AddContactSheet(
        state = state,
        newContact = newContact,
        isOpen = state.isAddContactSheetOpen,
        onEvent = { event ->
            if (event is ContactListEvent.OnAddPhotoClicked) {
//                imagePicker.pickImage()
            }
            onEvent(event)
        },
    )
}