package com.lampa.flowstatetest.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lampa.flowstatetest.network.PostRepository
import com.lampa.flowstatetest.network.model.PostResponseItem
import com.lampa.flowstatetest.ui.utils.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostViewModel @ViewModelInject constructor(
    private val postRepository: PostRepository
) : ViewModel() {

    private val _state = MutableStateFlow<UiState>(UiState.Empty)
    val state: StateFlow<UiState> = _state

    private val _postList = MutableStateFlow<List<PostResponseItem?>?>(null)
    val postsList: StateFlow<List<PostResponseItem?>?> = _postList

    private val _post = MutableStateFlow<PostResponseItem?>(null)
    val post: StateFlow<PostResponseItem?> = _post

    fun getPostList() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.emit(UiState.Loading)
            val response = postRepository.getPostList()
            if (response.isSuccessful) {
                _postList.emit(response.body())
                _state.emit(UiState.Success)
            } else {
                _state.emit(UiState.Error(response.message()))
            }
        }
    }

    fun getPost(id: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.emit(UiState.Loading)
            val response = postRepository.getPost(id)
            if (response.isSuccessful) {
                _post.emit(response.body())
                _state.emit(UiState.Success)
            } else {
                _state.emit(UiState.Error(response.message()))
            }
        }
    }
}