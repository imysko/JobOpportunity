package com.imysko.jobopportunity.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.imysko.jobopportunity.domain.usecase.GetFavoriteVacanciesCountUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Provider

class MainActivityViewModel(
    private val getFavoriteVacanciesCountUseCase: GetFavoriteVacanciesCountUseCase,
) : ViewModel() {

    private val _favoriteVacanciesCount = MutableStateFlow(0)
    val favoriteVacanciesCount: StateFlow<Int>
        get() = _favoriteVacanciesCount.asStateFlow()

    init {
        getFavoriteVacanciesCount()
    }

    private fun getFavoriteVacanciesCount() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getFavoriteVacanciesCountUseCase().collectLatest { count ->
                    _favoriteVacanciesCount.update { count }
                }
            }
        }
    }

    class Factory @Inject constructor(
        private val getFavoriteVacanciesCountUseCase: Provider<GetFavoriteVacanciesCountUseCase>,
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == MainActivityViewModel::class.java)
            return MainActivityViewModel(
                getFavoriteVacanciesCountUseCase.get(),
            ) as T
        }
    }
}
