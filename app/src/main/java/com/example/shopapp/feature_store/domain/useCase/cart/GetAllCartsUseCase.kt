package com.example.shopapp.feature_store.domain.useCase.cart

import com.example.shopapp.feature_store.data.dto.ProductWithCart
import com.example.shopapp.feature_store.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull

class GetAllCartsUseCase(private val cartRepository: CartRepository) {
    operator fun invoke(): Flow<List<ProductWithCart>> {
        return cartRepository.getAllCartItems().filterNotNull()
    }
}