package com.example.shopapp.feature_store.domain.useCase.product

import com.example.shopapp.common.domain.use_case.GetCartByProductIdUseCase
import com.example.shopapp.common.domain.use_case.InsertOrUpdateCartUseCase

data class ProductUseCase(
    val getProductsUseCase: GetProductsUseCase,
    val getCartByProductIdUseCase: GetCartByProductIdUseCase,
    val insertOrUpdateCartUseCase: InsertOrUpdateCartUseCase,
    val refreshRemoteDataUseCase: RefreshRemoteDataUseCase,
)
