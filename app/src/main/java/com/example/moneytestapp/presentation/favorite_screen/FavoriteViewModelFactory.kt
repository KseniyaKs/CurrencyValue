package com.example.moneytestapp.presentation.favorite_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moneytestapp.domain.CurrencyInteractor
import javax.inject.Inject


class FavoriteViewModelFactory @Inject constructor(val interactor: CurrencyInteractor) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavoriteViewModel(interactor = interactor) as T
    }

}
