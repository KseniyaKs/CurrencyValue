package com.example.moneytestapp.presentation.popular_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneytestapp.domain.CurrencyInteractor
import com.example.moneytestapp.domain.CurrencyModel
import com.example.moneytestapp.presentation.sorting_screen.SortingModel
import com.example.moneytestapp.presentation.sorting_screen.SortingParameter
import com.example.moneytestapp.presentation.sorting_screen.SortingType
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class PopularViewModel @Inject constructor(
    val interactor: CurrencyInteractor
) : ViewModel() {
    private val _currencyList = MutableStateFlow<List<CurrencyModel>>(listOf())
    val currencyList: StateFlow<List<CurrencyModel>> = _currencyList

    private val _currency = MutableSharedFlow<CurrencyModel?>()
    val currency: SharedFlow<CurrencyModel?> = _currency

    val handler = CoroutineExceptionHandler { context, exception ->
        Log.d("EXCEPTION", "Caught $exception")
    }

    fun loadLatestCurrency(currency: String) {
        viewModelScope.launch(Dispatchers.Default + handler) {
            _currencyList.value = interactor.getLatestCurrency(currency)
        }
    }

    fun onLikePressed(currencyModel: CurrencyModel) {
        viewModelScope.launch(Dispatchers.IO) {
            currencyModel.isLike = true
            _currency.emit(currencyModel)

            interactor.currencyLocalSave(currencyModel)
        }
    }

    fun onUnlikePressed(currencyModel: CurrencyModel) {
        viewModelScope.launch {
            currencyModel.isLike = false
            _currency.emit(currencyModel)

            interactor.currencyLocalDelete(currencyModel)
        }
    }

    fun onSortList(sortingModel: SortingModel, list: List<CurrencyModel>): List<CurrencyModel> {
        return when (sortingModel) {
            SortingModel(
                type = SortingType.ALPHABET,
                parameter = SortingParameter.ASCENDING
            ) -> list.sortedBy { it.code }
            SortingModel(
                type = SortingType.ALPHABET,
                parameter = SortingParameter.DESCENDING
            ) -> list.sortedByDescending { it.code }
            SortingModel(
                type = SortingType.VALUE,
                parameter = SortingParameter.ASCENDING
            ) -> list.sortedBy { it.value.toFloat() }
            else ->
                list.sortedByDescending { it.value.toFloat() }
        }
    }
}