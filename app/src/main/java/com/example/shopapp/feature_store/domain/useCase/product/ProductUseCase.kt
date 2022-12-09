package com.example.shopapp.feature_store.domain.useCase.product

import com.example.shopapp.common.domain.use_case.GetCartByProductIdUseCase
import com.example.shopapp.common.domain.use_case.InsertOrUpdateCartUseCase
import com.example.shopapp.feature_auth.domain.use_case.LogOutUseCase

data class ProductUseCase(
    val getProductsUseCase: GetProductsUseCase,
    val getCartByProductIdUseCase: GetCartByProductIdUseCase,
    val insertOrUpdateCartUseCase: InsertOrUpdateCartUseCase,
    val refreshRemoteDataUseCase: RefreshRemoteDataUseCase,
    val logOutUseCase: LogOutUseCase,
)
