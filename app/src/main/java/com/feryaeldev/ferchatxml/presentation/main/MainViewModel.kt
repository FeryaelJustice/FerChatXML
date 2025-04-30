package com.feryaeldev.ferchatxml.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feryaeldev.ferchatxml.domain.usecase.GetUsernameUseCase
import com.feryaeldev.ferchatxml.domain.usecase.SaveUsernameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val saveUsernameUseCase: SaveUsernameUseCase,
    private val getUsernameUseCase: GetUsernameUseCase
) :
    ViewModel() {

    private var _uiState = MutableStateFlow<MainViewState>(MainViewState.LOADING)
    val uiState: StateFlow<MainViewState> = _uiState

    init {
        verifyUserLogged()
    }

    private fun verifyUserLogged() {
        viewModelScope.launch {
            val name = async { getUsernameUseCase() }.await()
            if (name.isNotEmpty()) {
                _uiState.value = MainViewState.REGISTERED
            } else {
                _uiState.value = MainViewState.UNREGISTERED
            }
        }
    }

    fun saveUsername(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            saveUsernameUseCase(username)
        }
    }
}