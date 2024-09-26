package com.example.contactapp.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<T : Any>(initialState: T) : ViewModel(){
    private val _uiState = MutableStateFlow(initialState)
    val uiState get() = _uiState.asStateFlow()

    protected fun setState(callback: (T) -> T) {
        val newState = callback(_uiState.value)
        _uiState.update { newState }
    }

    protected suspend fun setStateAsync(function: suspend (T) -> T) {
        val newState = function(_uiState.value)
        _uiState.update { newState }
    }
}