package com.example.shopapp.feature_auth.domain.use_case

data class AuthUseCase(
    val loginUseCase: LoginUseCase,
    val autoLoginUseCase: AutoLoginUseCase,
    val logOutUseCase: LogOutUseCase
)
