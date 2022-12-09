package com.example.shopapp.feature_store.domain.useCase.product

import com.example.shopapp.common.utils.Resource
import com.example.shopapp.feature_store.domain.repository.ProductRepository

class RefreshRemoteDataUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(): Resource<Unit> {
        return productRepository.refreshRemoteData()
    }
}