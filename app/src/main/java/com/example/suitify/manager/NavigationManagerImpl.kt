package com.example.suitify.manager

import com.example.core.EMPTY_STRING
import com.example.core.managers.NavigationManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull

class NavigationManagerImpl : NavigationManager {

    private val destinationFlow = MutableStateFlow(EMPTY_STRING to false)

    override fun destinationFlow() = destinationFlow.asStateFlow().filterNotNull()

    override fun navigateTo(destination: String, isClearBackStack: Boolean) {
        destinationFlow.tryEmit(Pair(destination, isClearBackStack))
    }
}