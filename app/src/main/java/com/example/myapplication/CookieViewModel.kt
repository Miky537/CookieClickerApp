package com.example.myapplication

import android.app.Application
import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.*


class CookieViewModel(application: Application) : AndroidViewModel(application) {
    private val preferences: SharedPreferences =
        application.getSharedPreferences("cookie_prefs", Application.MODE_PRIVATE)

    private val model = CookieModel(preferences = preferences)
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
                model.incrementCookiesBy(cookiesFromGrandmas + cookiesFromFactories)
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