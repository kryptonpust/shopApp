package com.example.shopapp.feature_store.data

import com.example.shopapp.feature_store.data.dto.ProductDto
import retrofit2.http.GET

interface ProductApi {

    @GET("products")
    suspend fun getProducts():List<ProductDto>
}