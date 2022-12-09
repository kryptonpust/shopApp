package com.example.shopapp.feature_auth.data.repository

import android.app.Application
import com.example.shopapp.ShopApp
import com.example.shopapp.common.utils.Resource
import com.example.shopapp.feature_auth.data.AuthApiService
import com.example.shopapp.feature_auth.data.dto.LoginRequest
import com.example.shopapp.feature_auth.domain.model.ITokenModel
import com.example.shopapp.feature_auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException

class AuthRepositoryImpl(private val application: Application, private val authApiService: AuthApiService): AuthRepository {
    override suspend fun login(userName: String, password: String, rememberMe: Boolean): Resource<Unit> {
        //logic for network login
        try {
            val res=authApiService.loginUser(LoginRequest(username = userName, password = password))
            return if(res.isSuccessful) {
                res.body()?.let{
                    if(application is ShopApp)
                    {
                        application.TOKEN= it.token
                    }
                }
                Resource.Success(Unit)
            }else{
                Resource.Error(message = "Login Failed: ${if (res.code()==401) "Unauthorized" else res.code()}")
            }
        }catch (e: IOException) {
            return Resource.Error( message = "Could not reach the server, please check your internet connection!")
        } catch (e: HttpException) {
            return Resource.Error(message = "Oops, something went wrong!")
        }

    }

    override fun getTokenFlow(): Flow<ITokenModel?> {
        TODO("Not yet implemented")
    }

    override suspend fun getToken(): ITokenModel? {
        TODO("Not yet implemented")
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }


}