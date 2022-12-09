package com.example.shopapp.feature_auth.domain.use_case

import android.app.Application
import com.example.shopapp.ShopApp
import com.example.shopapp.common.utils.Resource
import com.example.shopapp.feature_auth.domain.repository.AuthRepository

class LogOutUseCase(private val application: Application, private val authRepository: AuthRepository) {
    suspend operator fun invoke()
    {
        //logic
        if (application is ShopApp) {
            application.TOKEN = null
        }
        authRepository.clear()
    }
}