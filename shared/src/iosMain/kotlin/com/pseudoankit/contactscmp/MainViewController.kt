package com.pseudoankit.contactscmp

import androidx.compose.ui.window.ComposeUIViewController
import com.pseudoankit.contactscmp.core.ui.App
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle

fun MainViewController() = ComposeUIViewController {
    App(
        darkTheme = UIScreen.mainScreen.traitCollection.userInterfaceStyle == UIUserInterfaceStyle.UIUserInterfaceStyleDark,
        dynamicColor = false
    )
}