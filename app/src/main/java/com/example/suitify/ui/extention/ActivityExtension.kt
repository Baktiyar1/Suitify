package com.example.suitify.ui.extention

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.example.data.utils.PACKAGE_KEY

fun Activity.openAppSettings() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts(PACKAGE_KEY, packageName, null)
    ).also { startActivity(it) }
}