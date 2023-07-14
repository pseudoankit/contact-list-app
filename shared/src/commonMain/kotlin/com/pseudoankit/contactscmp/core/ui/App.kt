package com.pseudoankit.contactscmp.core.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.pseudoankit.contactscmp.contacts.presentation.ContactsListViewModel
import com.pseudoankit.contactscmp.contacts.presentation.ui.ContactListScreen
import com.pseudoankit.contactscmp.core.presentation.ContactsTheme
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory

@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean
) {
    ContactsTheme(darkTheme = darkTheme, dynamicColor = dynamicColor) {
        val viewModel = getViewModel(
            key = "contact-list-screen",
            factory = viewModelFactory { ContactsListViewModel() }
        )

        val state by viewModel.state.collectAsState()

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            ContactListScreen(
                state = state,
                newContact = viewModel.newContact,
                onEvent = viewModel::onEvent
            )
        }
    }
}