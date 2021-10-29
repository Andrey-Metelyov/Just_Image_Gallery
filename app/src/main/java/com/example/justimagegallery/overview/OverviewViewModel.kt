package com.example.justimagegallery.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.justimagegallery.network.PicsumApi
import com.example.justimagegallery.network.PicsumPhoto
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {
    private val _photos = MutableLiveData<List<PicsumPhoto>>()

    val photos: LiveData<List<PicsumPhoto>>
        get() = _photos

    private val _navigateToSecondPhoto = MutableLiveData<Boolean?>()

    val navigateToSecondPhoto: LiveData<Boolean?>
        get() = _navigateToSecondPhoto

    fun doneNavigating() {
        _navigateToSecondPhoto.value = null
    }

    init {
        getPicsumPhotos()
    }

    private fun getPicsumPhotos() {
        viewModelScope.launch {
            try {
                val listResult = PicsumApi.retrofitService.getPhotos()
                _photos.value = listResult
            } catch (e: Exception) {
                _photos.value = ArrayList()
            }
        }
    }

    fun onButtonClick() {
        viewModelScope.launch {
            System.err.println("onButtonClick")
            _navigateToSecondPhoto.value = true
        }
    }
}
