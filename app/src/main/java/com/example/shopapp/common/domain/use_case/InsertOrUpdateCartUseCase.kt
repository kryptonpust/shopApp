package com.example.shopapp.common.domain.use_case

import com.example.shopapp.feature_store.data.entity.Cart
import com.example.shopapp.feature_store.domain.repository.CartRepository

class InsertOrUpdateCartUseCase(val cartRepository: CartRepository) {

    suspend operator fun invoke(cart: Cart)
    {
        cartRepository.insert(cart)
    }
}