package com.example.moneytestapp.presentation

import androidx.lifecycle.ViewModel
import com.example.moneytestapp.data.UiState
import com.example.moneytestapp.presentation.sorting_screen.SortingModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MainActivityViewModel @Inject constructor() : ViewModel() {

    private val _currencyName = MutableStateFlow<UiState<String>>(UiState.Loading)
    val currency: StateFlow<UiState<String>> = _currencyName

    private val _currencySorting = MutableStateFlow<SortingModel?>(null)
    val currencySorting: StateFlow<SortingModel?> = _currencySorting


    fun setSelectedCurrency(currency: String) {
        val state = UiState.Success(currency)
        _currencyName.tryEmit(state)
    }

    fun setSortCurrency(sortingModel: SortingModel) {
        _currencySorting.tryEmit(sortingModel)
    }
}