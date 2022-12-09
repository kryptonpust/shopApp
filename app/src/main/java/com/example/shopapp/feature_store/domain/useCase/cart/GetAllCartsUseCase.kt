package com.example.shopapp.feature_store.domain.useCase.cart

import com.example.shopapp.feature_store.data.dto.ProductWithCart
import com.example.shopapp.feature_store.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

class GetAllCartsUseCase(private val cartRepository: CartRepository) {
    operator fun invoke(filter_text:String=""): Flow<List<ProductWithCart>> {
        return cartRepository.getAllCartItems().filterNotNull().map { it.filter { productwithcart -> productwithcart.product.title.lowercase().contains(filter_text.lowercase())  } }
    }
}