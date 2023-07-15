package com.pseudoankit.contactscmp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pseudoankit.contactscmp.contacts.presentation.ui.ContactListScreen
import com.pseudoankit.contactscmp.core.presentation.ContactsTheme
import com.pseudoankit.contactscmp.core.presentation.ImagePicker
import com.pseudoankit.contactscmp.di.AppModule

@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    appModule: AppModule,
    imagePicker: ImagePicker
) {
    ContactsTheme(darkTheme = darkTheme, dynamicColor = dynamicColor) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            ContactListScreen(
                appModule = appModule,
                imagePicker = imagePicker
            )
        }
    }
}