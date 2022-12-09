package com.example.shopapp.feature_store.domain.useCase.cart

import com.example.shopapp.common.domain.use_case.InsertOrUpdateCartUseCase

data class CartUseCase(
    val getAllCartsUseCase: GetAllCartsUseCase,
    val insertOrUpdateCartUseCase: InsertOrUpdateCartUseCase,
    val deleteCartByIdUseCase: DeleteCartByIdUseCase,
)
