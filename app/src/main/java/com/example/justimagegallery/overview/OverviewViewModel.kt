package com.example.justimagegallery.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.justimagegallery.network.PicsumApi
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {
    private val _status = MutableLiveData<String>()

    val status: LiveData<String> = _status

    init {
        getPicsumPhotos()
    }

    private fun getPicsumPhotos() {
        viewModelScope.launch {
            val listResult = PicsumApi.retrofitService.getPhotos()
            val str = listResult.joinToString { it.downloadUrl }
            _status.value = str
        }
    }
}
