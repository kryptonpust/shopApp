package com.example.shopapp.feature_store.domain.repository

import com.example.shopapp.feature_store.data.dto.ProductWithCart
import com.example.shopapp.feature_store.data.entity.CartEntity
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getAllCartItems(): Flow<List<ProductWithCart>>
    suspend fun insert(cartEntity: CartEntity)
    fun getCartByProductId(id:Long): Flow<CartEntity?>
    suspend fun deleteCartById(id:Long)
}