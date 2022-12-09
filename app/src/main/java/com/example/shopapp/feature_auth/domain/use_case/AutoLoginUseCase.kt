package com.example.shopapp.feature_auth.domain.use_case

import com.example.shopapp.common.utils.Resource
import com.example.shopapp.feature_auth.domain.repository.AuthRepository

class AutoLoginUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(): Resource<Unit>
    {
        //logic
        authRepository.getToken()?.let {
            return if(it.remember)
            {
                Resource.Success(Unit)
            }else{
                authRepository.clear()
                Resource.Error("")
            }
        }
        return Resource.Error("")
    }
}