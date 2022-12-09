package com.example.shopapp.common.utils

object Utils {
    fun priceFormat(price: Float): String {
        return String.format("%.2f", price)
    }
}