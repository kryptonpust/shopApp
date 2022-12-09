package com.example.shopapp.feature_store.data.dto

import androidx.room.Embedded
import androidx.room.Relation
import com.example.shopapp.feature_store.data.entity.CartEntity
import com.example.shopapp.feature_store.data.entity.ProductEntity

data class ProductWithCart(
    @Embedded val cartEntity: CartEntity,
    @Relation(parentColumn = "productId",
        entityColumn = "id")
    val product: ProductEntity
)
