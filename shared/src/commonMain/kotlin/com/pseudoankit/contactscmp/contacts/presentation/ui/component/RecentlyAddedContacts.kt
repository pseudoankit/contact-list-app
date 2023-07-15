package com.pseudoankit.contactscmp.contacts.presentation.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pseudoankit.contactscmp.contacts.domain.Contact

@Composable
fun RecentlyAddedContacts(
    contacts: List<Contact>,
    onClick: (Contact) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        if (contacts.isNotEmpty()) {
            Text(
                text = "Recently Added",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(16.dp))
        }
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(contacts) { contact ->
                Column(
                    modifier = modifier.clickable(onClick = { onClick(contact) }),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ContactPhoto(
                        contact = contact,
                        modifier = Modifier.size(50.dp)
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = contact.firstName,
                    )
                }
            }
        }
    }
}