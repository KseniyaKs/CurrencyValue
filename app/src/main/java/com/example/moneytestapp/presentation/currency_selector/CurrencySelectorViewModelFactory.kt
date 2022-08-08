package com.example.moneytestapp.presentation.currency_selector

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moneytestapp.domain.CurrencyInteractor
import javax.inject.Inject

class CurrencySelectorViewModelFactory @Inject constructor(val interactor: CurrencyInteractor) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CurrencySelectorViewModel(interactor = interactor) as T
    }

}