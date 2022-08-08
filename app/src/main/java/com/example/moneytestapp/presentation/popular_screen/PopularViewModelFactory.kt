package com.example.moneytestapp.presentation.popular_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moneytestapp.domain.CurrencyInteractor
import javax.inject.Inject


class PopularViewModelFactory @Inject constructor(val interactor: CurrencyInteractor) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PopularViewModel(interactor = interactor) as T
    }

}
