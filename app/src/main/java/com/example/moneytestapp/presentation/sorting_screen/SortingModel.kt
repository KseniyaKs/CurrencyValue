package com.example.moneytestapp.presentation.sorting_screen

data class SortingModel(
    val type: SortingType?,
    val parameter: SortingParameter?
)

enum class SortingType{
    ALPHABET,
    VALUE
}

enum class SortingParameter{
    ASCENDING,
    DESCENDING
}
