package com.example.shopapp.feature_auth.domain.use_case

import com.example.shopapp.common.utils.Resource
import com.example.shopapp.feature_auth.domain.repository.AuthRepository

class LoginUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(
        userName:String,
        password:String,
        rememberMe:Boolean,
    ): Resource<Unit>
    {
        //logic for validate username and password
        if(userName.isBlank())
        {
            return Resource.Error(message = "User name cannot be blank")
        }

        if(password.isBlank())
        {
            return Resource.Error(message = "Password cannot be blank")
        }

        return authRepository.login(userName,password,rememberMe)
    }
}