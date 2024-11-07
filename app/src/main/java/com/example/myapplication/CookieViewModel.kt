package com.example.myapplication

import android.app.Application
import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel

class CookieViewModel(application: Application) : AndroidViewModel(application) {
    private val preferences: SharedPreferences =
        application.getSharedPreferences("cookie_prefs", Application.MODE_PRIVATE)

    private val model = CookieModel(preferences = preferences)
    var totalCookies = mutableStateOf(model.totalCookies)
    var grandmas = mutableStateOf(model.grandmas)
    var factories = mutableStateOf(model.factories)

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
        model.startProduction { newTotalCookies ->
            totalCookies.value = newTotalCookies
        }
    }
    override fun onCleared() {
        super.onCleared()
        model.stopProduction()
    }
}
