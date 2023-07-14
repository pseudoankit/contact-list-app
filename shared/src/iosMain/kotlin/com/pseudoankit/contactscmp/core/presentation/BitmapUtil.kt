package com.pseudoankit.contactscmp.core.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asComposeImageBitmap
import org.jetbrains.skia.Bitmap
import org.jetbrains.skia.Image

@Composable
actual fun rememberBitmapFromBytes(bytes: ByteArray?): ImageBitmap? {
    return remember(bytes) {
        bytes?.run {
            Bitmap.makeFromImage(
                Image.makeFromEncoded(bytes)
            ).asComposeImageBitmap()
        }
    }
}