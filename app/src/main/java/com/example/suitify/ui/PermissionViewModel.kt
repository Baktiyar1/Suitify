package com.example.suitify.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PermissionViewModel : ViewModel() {

    private val _isGrantedPermission = MutableStateFlow(value = false)
    val isGrantedPermission = _isGrantedPermission.asStateFlow()

    val visiblePermissionDialogQueue = mutableStateListOf<String>()

    fun dismissDialog() {
        visiblePermissionDialogQueue.removeFirst()
    }

    fun onPermissionResult(permission: String, isGranted: Boolean) {
        if (isGranted) _isGrantedPermission.tryEmit(value = true)
        else visiblePermissionDialogQueue.add(element = permission)
    }

}