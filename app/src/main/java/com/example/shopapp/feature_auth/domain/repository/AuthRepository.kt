package com.example.shopapp.feature_auth.domain.repository

import com.example.shopapp.feature_auth.domain.model.ITokenModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(userName: String, password: String, rememberMe: Boolean)
    fun getTokenFlow(): Flow<ITokenModel?>
    suspend fun getToken():ITokenModel?
    suspend fun logout()

}