package com.example.suitify.ui.screens.main_activity.permissions

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PermissionViewModel @Inject constructor() : ViewModel() {

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