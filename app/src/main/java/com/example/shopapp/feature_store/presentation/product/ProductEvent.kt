package com.example.shopapp.feature_store.presentation.product

import com.example.shopapp.feature_store.data.entity.ProductEntity

sealed class ProductEvent{
    data class ProductSelected(val product: ProductEntity): ProductEvent()
    data class Search(val filter_text:String):ProductEvent()
    data class Logout(val reason:String="normal"):ProductEvent()
}
