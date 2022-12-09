package com.example.shopapp.feature_store.domain.repository

import com.example.shopapp.feature_store.data.dto.ProductWithCart
import com.example.shopapp.feature_store.data.entity.Cart
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getAllCartItems(): Flow<List<ProductWithCart>>
    suspend fun insert(cart: Cart)
    fun getCartByProductId(id:Long): Flow<Cart?>
    suspend fun deleteCartById(id:Long)
}