package com.example.shopapp.feature_store.presentation.cart

import com.example.shopapp.feature_store.data.entity.CartEntity

sealed class CartEvent{
    data class UpdateCart(val cartEntity: CartEntity):CartEvent()
    data class DeleteCart(val id:Long):CartEvent()
    data class Search(val filter_text:String): CartEvent()
}
