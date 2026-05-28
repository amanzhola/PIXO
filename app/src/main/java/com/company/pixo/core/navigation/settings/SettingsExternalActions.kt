package com.company.pixo.core.navigation.settings

import android.app.Activity
import android.content.Intent
import androidx.core.net.toUri

fun Activity.openContactEmail() {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = "mailto:support@pixo.app".toUri()
        putExtra(Intent.EXTRA_EMAIL, arrayOf("support@pixo.app"))
        putExtra(Intent.EXTRA_SUBJECT, "PIXO Support")
    }

    startActivity(Intent.createChooser(intent, "Contact Us"))
}

fun Activity.shareWithFriends() {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, "Try PIXO — AI photo editor.")
    }

    startActivity(Intent.createChooser(intent, "Share PIXO"))
}

fun Activity.openPrivacyPolicy() {
    openUrl("https://pixo.app/privacy")
}

fun Activity.openTermsOfUse() {
    openUrl("https://pixo.app/terms")
}

private fun Activity.openUrl(url: String) {
    val intent = Intent(
        Intent.ACTION_VIEW,
        url.toUri()
    )

    startActivity(intent)
}