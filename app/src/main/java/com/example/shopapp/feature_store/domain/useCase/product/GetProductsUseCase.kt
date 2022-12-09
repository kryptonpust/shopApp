package com.example.shopapp.feature_store.domain.useCase.product

import com.example.shopapp.feature_store.data.entity.ProductEntity
import com.example.shopapp.feature_store.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class GetProductsUseCase(
    private val productRepository: ProductRepository
) {
    operator fun invoke(filter_text:String=""): Flow<List<ProductEntity>>
    {
        return productRepository.getProducts().map { it.filter { product -> product.title.lowercase().contains(filter_text.lowercase())  } }
    }
}