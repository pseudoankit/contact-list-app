package com.pseudoankit.contactscmp.core.data

import android.content.Context
import kotlinx.coroutines.Dispatchers
import java.util.UUID

actual class ImageStorage(
    private val context: Context
) {
    actual suspend fun saveImage(bytes: ByteArray): String = with(Dispatchers.IO) {
        val fileName = UUID.randomUUID().toString() + ".jpg"
        context.openFileOutput(fileName, Context.MODE_PRIVATE).use { outputStream ->
            outputStream.write(bytes)
        }
        fileName
    }

    actual suspend fun getImage(fileName: String): ByteArray? = with(Dispatchers.IO) {
        context.openFileInput(fileName).use {
            it.readBytes()
        }
    }

    actual suspend fun deleteImage(fileName: String) {
        with(Dispatchers.IO) {
            context.deleteFile(fileName)
        }
    }
}
