package com.example.myapplication

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class CookieViewModel : ViewModel() {
    private val model = CookieModel()

    var totalCookies = mutableStateOf(model.totalCookies)

    var grandmas = mutableStateOf(model.grandmas)

    var factories = mutableStateOf(model.factories)

    private var job: Job? = null

    init {
        startCookieProduction()
    }

    fun onCookieClicked() {
        model.incrementCookies()
        totalCookies.value = model.totalCookies
    }

    fun resetCookies() {
        model.resetCookies()
        totalCookies.value = model.totalCookies
        grandmas.value = model.grandmas
        factories.value = model.factories
    }

    fun buyGrandma() {
        if (model.buyGrandma()) {
            totalCookies.value = model.totalCookies
            grandmas.value = model.grandmas
        }
    }

    fun buyFactory() {
        if (model.buyFactory()) {
            totalCookies.value = model.totalCookies
            factories.value = model.factories
        }
    }

    private fun startCookieProduction() {
        job = CoroutineScope(Dispatchers.IO).launch {
            while (isActive) {
                delay(1000)
                val cookiesFromGrandmas = model.grandmas * 2
                val cookiesFromFactories = model.factories * 100
                model.totalCookies += cookiesFromGrandmas + cookiesFromFactories
                withContext(Dispatchers.Main) {
                    totalCookies.value = model.totalCookies
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}
