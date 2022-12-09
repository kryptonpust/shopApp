package com.example.shopapp.feature_store.data.repository

import com.example.shopapp.feature_store.data.entity.ProductEntity
import com.example.shopapp.feature_store.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class ProductRepositoryImpl():ProductRepository {
    override fun getProducts(): Flow<List<ProductEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshRemoteData() {
        TODO("Not yet implemented")
    }
}