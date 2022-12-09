package com.example.shopapp.feature_store.data.dto

import androidx.room.Embedded
import androidx.room.Relation
import com.example.shopapp.feature_store.data.entity.Cart
import com.example.shopapp.feature_store.data.entity.ProductEntity

data class ProductWithCart(
    @Embedded val cart: Cart,
    @Relation(parentColumn = "productId",
        entityColumn = "id")
    val product: ProductEntity
)
