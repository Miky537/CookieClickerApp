package com.example.myapplication

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FoodTextViewModel(private val repository: FoodTextModel = FoodTextModel()) : ViewModel() {
    private val _quote = mutableStateOf<FoodText?>(null)
    val quote: State<FoodText?> get() = _quote

    fun fetchQuote() {
        viewModelScope.launch {
            _quote.value = repository.fetchQuoteOfTheDay() ?: FoodText("Failed to load quote")
        }
    }
}