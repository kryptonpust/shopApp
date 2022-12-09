package com.example.shopapp.feature_store.presentation.cart

import com.example.shopapp.feature_store.data.entity.Cart

sealed class CartEvent{
    data class UpdateCart(val cart: Cart):CartEvent()
    data class DeleteCart(val id:Long):CartEvent()
    data class Search(val filter_text:String): CartEvent()
}
