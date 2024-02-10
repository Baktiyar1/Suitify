package com.example.suitify.ui.screens.home.models

import androidx.compose.runtime.Stable
import javax.annotation.concurrent.Immutable

sealed class HomeUiState {

    @Immutable
    data object Initial : HomeUiState()

    @Immutable
    data object Loading : HomeUiState()

    @Stable
    data class Loaded(val homeUiStateModel: HomeUiStateModel) : HomeUiState()

    @Immutable
    data class Error(val errorMessage: String) : HomeUiState()

}