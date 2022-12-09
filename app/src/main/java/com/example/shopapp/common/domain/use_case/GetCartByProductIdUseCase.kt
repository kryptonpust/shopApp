package com.example.shopapp.common.domain.use_case

import com.example.shopapp.feature_store.data.entity.CartEntity
import com.example.shopapp.feature_store.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull

class GetCartByProductIdUseCase(
    private val cartRepository: CartRepository
) {
    operator fun invoke(id:Long): Flow<CartEntity>
    {
        return cartRepository.getCartByProductId(id).filterNotNull()
    }
}