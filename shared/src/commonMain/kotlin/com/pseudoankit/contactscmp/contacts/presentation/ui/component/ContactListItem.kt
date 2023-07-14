package com.pseudoankit.contactscmp.contacts.presentation.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pseudoankit.contactscmp.contacts.domain.Contact

@Composable
fun ContactListItem(
    contact: Contact,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ContactPhoto(contact = contact, iconSize = 50.dp)
        Spacer(Modifier.width(16.dp))
        Text(
            text = "${contact.firstName} ${contact.lastName}",
            modifier = Modifier.weight(1f)
        )
    }
}