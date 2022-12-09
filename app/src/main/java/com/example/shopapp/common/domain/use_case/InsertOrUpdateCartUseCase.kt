package com.example.shopapp.common.domain.use_case

import com.example.shopapp.feature_store.data.entity.CartEntity
import com.example.shopapp.feature_store.domain.repository.CartRepository

class InsertOrUpdateCartUseCase(val cartRepository: CartRepository) {

    suspend operator fun invoke(cartEntity: CartEntity)
    {
        cartRepository.insert(cartEntity)
    }
}