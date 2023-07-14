package com.pseudoankit.contactscmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform