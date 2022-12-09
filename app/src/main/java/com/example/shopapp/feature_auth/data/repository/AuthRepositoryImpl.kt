package com.example.shopapp.feature_auth.data.repository

import com.example.shopapp.feature_auth.domain.model.ITokenModel
import com.example.shopapp.feature_auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(): AuthRepository {
    override suspend fun login(userName: String, password: String, rememberMe: Boolean) {
        TODO("Not yet implemented")
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