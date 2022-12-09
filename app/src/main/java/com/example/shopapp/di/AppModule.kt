package com.example.shopapp.di

import android.app.Application
import androidx.room.Room
import com.example.shopapp.BuildConfig
import com.example.shopapp.common.data.LocalDatabase
import com.example.shopapp.feature_auth.data.AuthApiService
import com.example.shopapp.feature_auth.domain.use_case.AuthUseCase
import com.example.shopapp.feature_auth.domain.use_case.LoginUseCase
import com.example.shopapp.feature_auth.data.repository.AuthRepositoryImpl
import com.example.shopapp.feature_auth.domain.repository.AuthRepository
import com.example.shopapp.feature_auth.domain.use_case.AutoLoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalDatabase(app: Application): LocalDatabase {
        return Room.databaseBuilder(app, LocalDatabase::class.java, BuildConfig.DATABASE_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApiService(retrofit: Retrofit): AuthApiService
    {
        return retrofit.create(AuthApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        application: Application,
        authApiService: AuthApiService,
        localDatabase: LocalDatabase
    ): AuthRepository
    {
        return AuthRepositoryImpl(application,authApiService,localDatabase.authDao)
    }

    @Provides
    @Singleton
    fun provideAuthUseCase(authRepository: AuthRepository): AuthUseCase
    {
        return AuthUseCase(
            loginUseCase = LoginUseCase(authRepository),
            autoLoginUseCase = AutoLoginUseCase(authRepository)
        )
    }
}