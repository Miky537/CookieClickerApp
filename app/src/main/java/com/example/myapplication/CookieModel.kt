package com.example.myapplication

import android.content.SharedPreferences

data class CookieModel(
    var totalCookies: Int = 501,
    var grandmas: Int = 0,
    var factories: Int = 0,
    var cookiesPerSec: Int = 0,
    val preferences: SharedPreferences
) {
    init {
        totalCookies = preferences.getInt(TOTAL_COOKIES_KEY, 501)
        grandmas = preferences.getInt(GRANDMAS_KEY, 0)
        factories = preferences.getInt(FACTORIES_KEY, 0)
        cookiesPerSec = preferences.getInt(COOKIES_PER_SEC_KEY, 0)
    }

    fun incrementCookies() {
        totalCookies++
        preferences.edit().putInt(TOTAL_COOKIES_KEY, totalCookies).apply()
    }

    fun resetCookies() {
        totalCookies = 0
        preferences.edit().putInt(TOTAL_COOKIES_KEY, totalCookies).apply()
    }

    fun buyGrandma(): Boolean {
        if (totalCookies >= 500) {
            grandmas++
            totalCookies -= 500
            preferences.edit().putInt(GRANDMAS_KEY, grandmas).apply()
            preferences.edit().putInt(TOTAL_COOKIES_KEY, totalCookies).apply()
            return true
        }
        return false
    }

    fun buyFactory(): Boolean {
        if (totalCookies >= 10_000) {
            factories++
            totalCookies -= 10_000
            preferences.edit().putInt(FACTORIES_KEY, factories).apply()
            preferences.edit().putInt(TOTAL_COOKIES_KEY, totalCookies).apply()
            return true
        }
        return false
    }

    fun incrementCookiesBy(amountOfCookiesToUpdate: Int) {
        totalCookies += amountOfCookiesToUpdate
    }

    companion object {
        private const val TOTAL_COOKIES_KEY = "total_cookies"
        private const val GRANDMAS_KEY = "grandmas"
        private const val FACTORIES_KEY = "factories"
        private const val COOKIES_PER_SEC_KEY = "cookies_per_sec"
    }
}
