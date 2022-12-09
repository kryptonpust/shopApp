package com.example.shopapp.feature_store.presentation.cart

import com.example.shopapp.feature_store.data.dto.ProductWithCart

data class CartState(
    val productWithCart: List<ProductWithCart> = emptyList(),
    val isLoading: Boolean = true,
)