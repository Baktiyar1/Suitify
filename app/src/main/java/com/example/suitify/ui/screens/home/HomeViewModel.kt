package com.example.suitify.ui.screens.home

import androidx.lifecycle.SavedStateHandle
import com.example.core.managers.NavigationManager
import com.example.domain.base.Mapper
import com.example.domain.models.DomainMusic
import com.example.domain.use_cases.FetchAllMusicsObservableUseCase
import com.example.player.service.MusicServiceHandler
import com.example.suitify.base.BaseMusicViewModel
import com.example.suitify.models.Music
import com.example.suitify.ui.screens.home.models.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    navigatorManager: NavigationManager,
    musicServicesHandler: MusicServiceHandler,
    fetchAllMusicsObservableUseCase: FetchAllMusicsObservableUseCase,
    mapMusicListDomainToUi: Mapper<List<DomainMusic>, List<Music>>,
    savedStateHandle: SavedStateHandle
) : BaseMusicViewModel(
    navigatorManager = navigatorManager,
    musicServicesHandler = musicServicesHandler,
    fetchAllMusicsObservableUseCase = fetchAllMusicsObservableUseCase,
    mapMusicListDomainToUi = mapMusicListDomainToUi,
    savedStateHandle = savedStateHandle,
) {

    private val _isVisibleCategory = MutableStateFlow(value = false)
    val isVisibleCategory = _isVisibleCategory.asStateFlow()

    override fun onUiEvents(uiEvents: UiEvents) {
        when (uiEvents) {
            UiEvents.OnBackPressed -> changeBottomSheetVisibility(isBottomSheetVisibility = true)
            UiEvents.CategoryVisibilityChange -> onCategoryVisibilityChange()
            else -> onBaseUiEvents(uiEvents)
        }
    }

    private fun onCategoryVisibilityChange() {
        _isVisibleCategory.tryEmit(!_isVisibleCategory.value)
    }
}