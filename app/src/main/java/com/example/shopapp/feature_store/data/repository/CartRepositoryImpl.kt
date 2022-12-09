package com.example.shopapp.feature_store.data.repository

import com.example.shopapp.feature_store.data.CartDao
import com.example.shopapp.feature_store.data.dto.ProductWithCart
import com.example.shopapp.feature_store.data.entity.CartEntity
import com.example.shopapp.feature_store.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow

class CartRepositoryImpl(
    private val cartDao: CartDao
): CartRepository {
    override fun getAllCartItems(): Flow<List<ProductWithCart>> {
        return cartDao.getAllCartItems()
    }

    override suspend fun insert(cartEntity: CartEntity) {
        cartDao.insert(cartEntity)
    }


    override fun getCartByProductId(id: Long): Flow<CartEntity?> {
        return cartDao.getCartByProductId(id)
    }

    override suspend fun deleteCartById(id: Long) {
        cartDao.delete(id)
    }


}