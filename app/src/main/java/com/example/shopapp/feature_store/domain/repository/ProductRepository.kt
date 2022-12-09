package com.example.shopapp.feature_store.domain.repository

import com.example.shopapp.feature_store.data.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(): Flow<List<ProductEntity>>
    suspend fun refreshRemoteData()
}