package com.example.shopapp.di

import android.app.Application
import com.example.shopapp.BuildConfig
import com.example.shopapp.ShopApp
import com.example.shopapp.common.data.LocalDatabase
import com.example.shopapp.feature_store.data.ProductApi
import com.example.shopapp.feature_store.data.repository.ProductRepositoryImpl
import com.example.shopapp.feature_store.domain.repository.ProductRepository
import com.example.shopapp.feature_store.domain.useCase.product.GetProductsUseCase
import com.example.shopapp.feature_store.domain.useCase.product.ProductUseCase
import com.example.shopapp.feature_store.domain.useCase.product.RefreshRemoteDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {
    @Provides
    @ViewModelScoped
    fun provideAuthToken(application: Application): String? {
        if(application is ShopApp)
        {
            return application.TOKEN
        }
        return null
    }

    @Provides
    @ViewModelScoped
    fun provideOkHttpClient(token:String?): OkHttpClient
    {
        return OkHttpClient().newBuilder().addInterceptor {
            it.proceed(it.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            )
        }.build()
    }

    @Provides
    @ViewModelScoped
    @Named("authRetrofit")
    fun provideAuthRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @ViewModelScoped
    fun provideProductApi(@Named("authRetrofit") retrofit: Retrofit): ProductApi
    {
        return retrofit.create(ProductApi::class.java)
    }

    @Provides
    @ViewModelScoped
    fun provideProductRepository(productApi: ProductApi, localDatabase: LocalDatabase): ProductRepository {
        return ProductRepositoryImpl(productApi,localDatabase.productDao)
    }

    @Provides
    @ViewModelScoped
    fun provideProductUseCase(productRepository: ProductRepository): ProductUseCase
    {
        return ProductUseCase(
            getProductsUseCase = GetProductsUseCase(productRepository=productRepository),
            refreshRemoteDataUseCase = RefreshRemoteDataUseCase(productRepository=productRepository)
        )
    }
}