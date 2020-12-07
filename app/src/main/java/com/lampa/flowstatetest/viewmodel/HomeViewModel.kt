package com.lampa.flowstatetest.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lampa.flowstatetest.network.Repository
import com.lampa.flowstatetest.network.model.PostResponseItem
import com.lampa.flowstatetest.ui.utils.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.math.log
import kotlin.system.measureTimeMillis

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

    fun flowList() {
        val list = arrayListOf(1, 2, 3, 4, 5, 6)
        val newList = arrayListOf<String>()
        viewModelScope.launch {
            val listFlow = list.asFlow()
                .filter {
                    it % 2 == 0
                }
                .map {
                    "$it"
                }
                .collect {
                    newList.add(it)
                }

            newList.forEach {
                Timber.d(it)
            }
        }
    }

    fun parallel() {
        viewModelScope.launch {
            val time = measureTimeMillis {
                val firstRequest = async {
                    delay(2000)
                }
                val secondRequest = async {
                    delay(4000)
                }
                Timber.d("First request: ${firstRequest.await()}")
                Timber.d("Second request: ${secondRequest.await()}")
                Timber.d("First request: ${firstRequest.await()}, second request: ${secondRequest.await()}")
            }
            Timber.d("Total time: $time")
        }
    }
}