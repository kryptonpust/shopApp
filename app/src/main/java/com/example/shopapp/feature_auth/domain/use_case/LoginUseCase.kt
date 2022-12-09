package com.example.shopapp.feature_auth.domain.use_case

import android.app.Application
import com.example.shopapp.ShopApp
import com.example.shopapp.common.utils.Resource
import com.example.shopapp.feature_auth.domain.repository.AuthRepository

class LoginUseCase(
    private val application: Application,
    private val authRepository: AuthRepository) {
    suspend operator fun invoke(
        userName:String,
        password:String,
        rememberMe:Boolean,
    ): Resource<String>
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


        val temp=authRepository.login(userName,password,rememberMe)
        if(temp is Resource.Success)
        {
            if(application is ShopApp)
            {
                application.TOKEN= temp.data
            }
        }
        return temp
    }
}