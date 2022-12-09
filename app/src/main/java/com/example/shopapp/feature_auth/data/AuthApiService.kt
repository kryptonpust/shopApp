package com.example.shopapp.feature_auth.data

import com.example.shopapp.feature_auth.data.dto.LoginRequest
import com.example.shopapp.feature_auth.data.dto.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("auth/login")
    suspend fun loginUser(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>
}