package com.example.searchindictionary.ui.src.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchindictionary.ui.src.repository.MainRepository
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val mainRepository = MainRepository()

    val usersSuccessLiveData = mainRepository.searchSuccessLiveData
    val usersFailureLiveData = mainRepository.searchFailureLiveData

    fun getResult(query: String) {
        //this is coroutine viewmodel scope to call suspend fun of repository
        viewModelScope.launch { mainRepository.getSearchResult(query) }
    }

}