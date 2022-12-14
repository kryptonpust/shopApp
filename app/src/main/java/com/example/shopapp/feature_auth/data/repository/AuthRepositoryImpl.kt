package com.example.shopapp.feature_auth.data.repository

import android.app.Application
import com.example.shopapp.ShopApp
import com.example.shopapp.common.utils.Resource
import com.example.shopapp.feature_auth.data.AuthApiService
import com.example.shopapp.feature_auth.data.AuthDao
import com.example.shopapp.feature_auth.data.dto.LoginRequest
import com.example.shopapp.feature_auth.data.model.TokenEntity
import com.example.shopapp.feature_auth.domain.model.ITokenModel
import com.example.shopapp.feature_auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException

class AuthRepositoryImpl(
    private val authApiService: AuthApiService,
    private val authDao: AuthDao,
    ): AuthRepository {
    override suspend fun login(userName: String, password: String, rememberMe: Boolean): Resource<String> {
        //logic for network login
        try {
            val res=authApiService.loginUser(LoginRequest(username = userName, password = password))
            if(res.isSuccessful) {
                res.body()?.let{
                    authDao.insertToken(TokenEntity(null, it.token,rememberMe))

                    return Resource.Success(it.token)
                }
                return Resource.Error("No token found!!")
            }else{
                return Resource.Error(message = "Login Failed: ${if (res.code()==401) "Unauthorized" else res.code()}")
            }
        }catch (e: IOException) {
            return Resource.Error( message = "Could not reach the server, please check your internet connection!")
        } catch (e: HttpException) {
            return Resource.Error(message = "Oops, something went wrong!")
        }

    }

    override suspend fun autologin(): Resource<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun clear() {
        authDao.deleteToken()
    }

    override fun getTokenFlow(): Flow<ITokenModel?> {
        TODO("Not yet implemented")
    }

    override suspend fun getToken(): ITokenModel? {
        return authDao.getToken()
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }


}