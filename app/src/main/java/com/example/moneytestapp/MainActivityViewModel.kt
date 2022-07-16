package com.example.moneytestapp.presentation

import androidx.lifecycle.ViewModel
import com.example.moneytestapp.presentation.sorting_screen.SortingModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {

    private val _currencyName = MutableStateFlow<String?>(null)
    val currency: StateFlow<String?> = _currencyName

    private val _currencySorting = MutableStateFlow<SortingModel?>(null)
    val currencySorting: StateFlow<SortingModel?> = _currencySorting


    fun setSelectedCurrency(currency: String) {
        _currencyName.tryEmit(currency)
    }

    fun setSortCurrency(sortingModel: SortingModel) {
        _currencySorting.tryEmit(sortingModel)
    }
}