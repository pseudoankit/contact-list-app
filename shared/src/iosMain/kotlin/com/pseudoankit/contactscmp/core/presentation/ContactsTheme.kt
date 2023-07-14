package com.pseudoankit.contactscmp.core.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.pseudoankit.contactscmp.core.ui.DarkColorScheme
import com.pseudoankit.contactscmp.core.ui.LightColorScheme
import com.pseudoankit.contactscmp.core.ui.Typography

@Composable
actual fun ContactsTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}