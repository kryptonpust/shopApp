package com.example.shopapp.feature_store.presentation.product

import com.example.shopapp.feature_store.data.entity.ProductEntity

data class ProductState(
    val products: List<ProductEntity> = emptyList(),
    val selectedProduct: ProductEntity?=null,
    val isLoading: Boolean = true,
)
