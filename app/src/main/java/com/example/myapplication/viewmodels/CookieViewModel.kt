package com.example.myapplication.viewmodels

import android.app.Application
import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.example.myapplication.models.CookieModel

import java.math.BigInteger

class CookieViewModel(application: Application) : AndroidViewModel(application) {
    private val preferences: SharedPreferences =
        application.getSharedPreferences("cookie_prefs", Application.MODE_PRIVATE)

    private val model = CookieModel(preferences = preferences)
    var totalCookies = mutableStateOf(formatBigNumber(model.totalCookies))
    var ovens = mutableStateOf(model.ovens)
    var grandmas = mutableStateOf(model.grandmas)
    var factories = mutableStateOf(model.factories)

    init {
        startCookieProduction()
    }

    fun onCookieClicked() {
        model.incrementCookies()
        totalCookies.value = formatBigNumber(model.totalCookies)
    }

    fun resetCookies() {
        model.resetCookies()
        totalCookies.value = formatBigNumber(model.totalCookies)
        ovens.value = model.ovens
        grandmas.value = model.grandmas
        factories.value = model.factories
    }

    fun buyOven() {
        if (model.buyOven()) {
            totalCookies.value = formatBigNumber(model.totalCookies)
            ovens.value = model.ovens
        }
    }

    fun buyGrandma() {
        if (model.buyGrandma()) {
            totalCookies.value = formatBigNumber(model.totalCookies)
            grandmas.value = model.grandmas
        }
    }

    fun buyFactory() {
        if (model.buyFactory()) {
            totalCookies.value = formatBigNumber(model.totalCookies)
            factories.value = model.factories
        }
    }

    private fun startCookieProduction() {
        model.startProduction { newTotalCookies ->
            totalCookies.value = formatBigNumber(newTotalCookies)
        }
    }

    override fun onCleared() {
        super.onCleared()
        model.stopProduction()
    }

    private fun formatBigNumber(number: BigInteger): String {
        val alphabet = "abcdefghijklmnopqrstuvwxyz"
        var value = number
        var suffix = ""

        while (value >= BigInteger.valueOf(1_000_000)) {
            val remainder = value % BigInteger.valueOf(1_000_000)
            value /= BigInteger.valueOf(1_000_000)
            val index = remainder.toInt() / 1_000_000
            suffix += alphabet[index % alphabet.length]
        }
        return "$value$suffix$suffix"
    }
}