package com.example.shopapp.feature_store.domain.useCase.cart

import com.example.shopapp.feature_store.domain.repository.CartRepository

class DeleteCartByIdUseCase(private val cartRepository: CartRepository) {
    suspend operator fun invoke(id:Long)
    {
        cartRepository.deleteCartById(id)
    }
}