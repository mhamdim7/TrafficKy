package com.mhamdi.trafficky.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhamdi.trafficky.LightStates
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RoadLightsViewModel : ViewModel() {

    // Expose screen UI state
    private val _uiState: MutableLiveData<LightStates> = MutableLiveData()
    val uiState: LiveData<LightStates> = _uiState

    // Handle business logic
    fun updateTrafficStates() = viewModelScope.launch {
        _uiState.postValue(LightStates.GREEN)
        delay(FROM_GREEN_TO_ORANGE_WAIT_TIME)
        _uiState.postValue(LightStates.ORANGE)
        delay(FROM_ORANGE_TO_RED_WAIT_TIME)
        _uiState.postValue(LightStates.RED)
    }

    companion object {
        const val FROM_GREEN_TO_ORANGE_WAIT_TIME = 3000L
        const val FROM_ORANGE_TO_RED_WAIT_TIME = 2000L
    }
}