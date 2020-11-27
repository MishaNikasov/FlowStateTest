package com.lampa.flowstatetest.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lampa.flowstatetest.network.Repository
import com.lampa.flowstatetest.network.model.PostResponseItem
import com.lampa.flowstatetest.ui.utils.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _state = MutableStateFlow<UiState>(UiState.Empty)
    val state: StateFlow<UiState> = _state

    private val _posts = MutableStateFlow<List<PostResponseItem?>?>(null)
    val posts: StateFlow<List<PostResponseItem?>?> = _posts

    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.emit(UiState.Loading)
            val response = repository.getPosts()
            if (response.isSuccessful) {
                _posts.emit(response.body())
                _state.emit(UiState.Success)
            } else {
                _state.emit(UiState.Error(response.message()))
            }
        }
    }
}