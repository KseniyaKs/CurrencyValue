package com.example.moneytestapp.presentation.favorite_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneytestapp.domain.CurrencyInteractor
import com.example.moneytestapp.domain.CurrencyModel
import com.example.moneytestapp.presentation.sorting_screen.SortingModel
import com.example.moneytestapp.presentation.sorting_screen.SortingParameter
import com.example.moneytestapp.presentation.sorting_screen.SortingType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel @Inject constructor(
    val interactor: CurrencyInteractor
) : ViewModel() {
    private val _currencyList = MutableStateFlow<List<CurrencyModel>>(listOf())
    val currencyList: StateFlow<List<CurrencyModel>> = _currencyList

    private val _currency = MutableSharedFlow<CurrencyModel?>()
    val currency: SharedFlow<CurrencyModel?> = _currency

    init {
        refresh()
    }

    private fun refresh() {
        viewModelScope.launch {
            _currencyList.value = interactor.getLocalSaveCurrency()
        }
    }

    fun onUnlikePressed(currencyModel: CurrencyModel) {
        viewModelScope.launch {
            currencyModel.isLike = false
            _currency.emit(currencyModel)

            interactor.currencyLocalDelete(currencyModel)
        }
        refresh()
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
