package com.example.shopapp.feature_store.data.repository

import com.example.shopapp.feature_store.data.CartDao
import com.example.shopapp.feature_store.data.dto.ProductWithCart
import com.example.shopapp.feature_store.data.entity.Cart
import com.example.shopapp.feature_store.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow

class CartRepositoryImpl(
    private val cartDao: CartDao
): CartRepository {
    override fun getAllCartItems(): Flow<List<ProductWithCart>> {
        return cartDao.getAllCartItems()
    }

    override suspend fun insert(cart: Cart) {
        cartDao.insert(cart)
    }


    override fun getCartByProductId(id: Long): Flow<Cart?> {
        return cartDao.getCartByProductId(id)
    }

    override suspend fun deleteCartById(id: Long) {
        cartDao.delete(id)
    }


}