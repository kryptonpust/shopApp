package com.example.shopapp.feature_auth.domain.use_case

import android.app.Application
import com.example.shopapp.ShopApp
import com.example.shopapp.common.utils.Resource
import com.example.shopapp.feature_auth.domain.repository.AuthRepository

class AutoLoginUseCase(private val application: Application,private val authRepository: AuthRepository) {
    suspend operator fun invoke(): Resource<Unit>
    {
        //logic
        authRepository.getToken()?.let {
            return if(it.remember)
            {
                if(application is ShopApp)
                {
                    application.TOKEN=it.token
                }
                Resource.Success(Unit)
            }else{
                if(application is ShopApp)
                {
                    application.TOKEN=null
                }
                authRepository.clear()
                Resource.Error("")
            }
        }
        return Resource.Error("")
    }
}