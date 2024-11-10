package com.example.myapplication.models

import android.content.SharedPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigInteger

data class CookieModel(
    var totalCookies: BigInteger = BigInteger.valueOf(0),
    var ovens: Int = 0,
    var grandmas: Int = 0,
    var factories: Int = 0,
    var cookiesPerSec: Int = 0,
    val preferences: SharedPreferences
) {
    private var productionJob: Job? = null

    init {
        totalCookies = try {
            BigInteger(preferences.getString(TOTAL_COOKIES_KEY, "0"))
        } catch (e: ClassCastException) {
            BigInteger.valueOf(preferences.getInt(TOTAL_COOKIES_KEY, 501).toLong())
        }
        ovens = preferences.getInt(OVENS_KEY, 0)
        grandmas = preferences.getInt(GRANDMAS_KEY, 0)
        factories = preferences.getInt(FACTORIES_KEY, 0)
        cookiesPerSec = preferences.getInt(COOKIES_PER_SEC_KEY, 0)
    }

    fun startProduction(onCookiesProduced: (BigInteger) -> Unit) {
        productionJob = CoroutineScope(Dispatchers.IO).launch {
            while (isActive) {
                delay(1000)
                val cookiesFromOvens = BigInteger.valueOf((ovens * 1).toLong())
                val cookiesFromGrandmas = BigInteger.valueOf((grandmas * 7).toLong())
                val cookiesFromFactories = BigInteger.valueOf((factories * 150).toLong())
                val cookiesProduced = cookiesFromOvens + cookiesFromGrandmas + cookiesFromFactories
                incrementCookiesBy(cookiesProduced)
                withContext(Dispatchers.Main) {
                    onCookiesProduced(totalCookies)
                }
            }
        }
    }

    fun incrementCookies() {
        totalCookies += BigInteger.ONE
        preferences.edit().putString(TOTAL_COOKIES_KEY, totalCookies.toString()).apply()
    }

    fun resetCookies() {
        totalCookies = BigInteger.ZERO
        preferences.edit().putString(TOTAL_COOKIES_KEY, totalCookies.toString()).apply()
    }

    fun buyOven(): Boolean {
        val cost = BigInteger.valueOf(100)
        if (totalCookies >= cost) {
            ovens++
            totalCookies -= cost
            preferences.edit().putInt(OVENS_KEY, ovens).apply()
            preferences.edit().putString(TOTAL_COOKIES_KEY, totalCookies.toString()).apply()
            return true
        }
        return false
    }

    fun buyGrandma(): Boolean {
        val cost = BigInteger.valueOf(500)
        if (totalCookies >= cost) {
            grandmas++
            totalCookies -= cost
            preferences.edit().putInt(GRANDMAS_KEY, grandmas).apply()
            preferences.edit().putString(TOTAL_COOKIES_KEY, totalCookies.toString()).apply()
            return true
        }
        return false
    }

    fun buyFactory(): Boolean {
        val cost = BigInteger.valueOf(10_000)
        if (totalCookies >= cost) {
            factories++
            totalCookies -= cost
            preferences.edit().putInt(FACTORIES_KEY, factories).apply()
            preferences.edit().putString(TOTAL_COOKIES_KEY, totalCookies.toString()).apply()
            return true
        }
        return false
    }

    fun incrementCookiesBy(amountOfCookiesToUpdate: BigInteger) {
        totalCookies += amountOfCookiesToUpdate
        preferences.edit().putString(TOTAL_COOKIES_KEY, totalCookies.toString()).apply()
    }

    fun stopProduction() {
        productionJob?.cancel()
    }

    companion object {
        private const val TOTAL_COOKIES_KEY = "total_cookies"
        private const val OVENS_KEY = "ovens"
        private const val GRANDMAS_KEY = "grandmas"
        private const val FACTORIES_KEY = "factories"
        private const val COOKIES_PER_SEC_KEY = "cookies_per_sec"
    }
}
