package com.example.myapplication

data class CookieModel(
    var totalCookies: Int = 501,
    var grandmas: Int = 0,
    var factories: Int = 0
) {
    fun incrementCookies() {
        totalCookies++
    }

    fun resetCookies() {
        totalCookies = 0
    }

    fun buyGrandma(): Boolean {
        if (totalCookies >= 500) {
            grandmas++;
            totalCookies -= 500
            return true
        }
        return false
    }

    fun buyFactory(): Boolean {
        if (totalCookies >= 10_000) {
            factories++;
            totalCookies -= 10_000
            return true
        }
        return false
    }
}