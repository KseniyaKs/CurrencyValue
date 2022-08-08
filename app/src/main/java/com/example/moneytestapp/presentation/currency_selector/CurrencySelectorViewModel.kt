package com.example.moneytestapp.presentation.currency_selector

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneytestapp.domain.CurrencyFullName
import com.example.moneytestapp.domain.CurrencyInteractor
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class CurrencySelectorViewModel @Inject constructor(
    val interactor: CurrencyInteractor
) : ViewModel() {

    private val _currencyList = MutableStateFlow<List<CurrencyFullName>>(listOf())
    val currencyList: StateFlow<List<CurrencyFullName>> = _currencyList

    private val _currencyName = MutableSharedFlow<CurrencyFullName?>()
    val currency: SharedFlow<CurrencyFullName?> = _currencyName

    val handler = CoroutineExceptionHandler { context, exception ->
        Log.d("EXCEPTION", "Caught $exception")
    }

    init {
        viewModelScope.launch(handler) {
            _currencyList.value = interactor.getCurrency()
        }
    }
}