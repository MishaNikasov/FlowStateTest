package com.lampa.flowstatetest.ui.utils

sealed class UiState {
    object Loading: UiState()
    object Empty: UiState()
    object Success: UiState()
    data class Error(val message: String): UiState()
}