package com.example.suitify.manager

import android.content.Context
import android.widget.Toast
import com.example.core.managers.ToastManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ToastManagerImpl @Inject constructor(@ApplicationContext private val context: Context) :
    ToastManager {
    override fun showToast(message: String) =
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}