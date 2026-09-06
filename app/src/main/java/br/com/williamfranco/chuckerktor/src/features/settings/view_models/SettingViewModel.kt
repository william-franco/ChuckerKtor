package br.com.williamfranco.chuckerktor.src.features.settings.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.williamfranco.chuckerktor.src.features.settings.models.SettingModel
import br.com.williamfranco.chuckerktor.src.features.settings.repositories.SettingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface SettingViewModel {
    val state: StateFlow<SettingModel>
    fun changeTheme(isDarkTheme: Boolean)
}

class SettingViewModelImpl(
    private val settingRepository: SettingRepository,
) : ViewModel(), SettingViewModel {

    private val _state = MutableStateFlow(SettingModel())
    override val state: StateFlow<SettingModel> = _state.asStateFlow()

    init {
        observeTheme()
    }

    private fun observeTheme() {
        settingRepository.theme
            .onEach { model -> _state.update { model } }
            .launchIn(viewModelScope)
    }

    override fun changeTheme(isDarkTheme: Boolean) {
        viewModelScope.launch {
            settingRepository.updateTheme(isDarkTheme = isDarkTheme)
        }
    }
}
