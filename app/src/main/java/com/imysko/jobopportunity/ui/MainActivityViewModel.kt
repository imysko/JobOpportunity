package com.imysko.jobopportunity.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class MainActivityViewModel(
) : ViewModel() {

    class Factory @Inject constructor() : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == MainActivityViewModel::class.java)
            return MainActivityViewModel() as T
        }
    }
}
