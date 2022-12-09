package com.example.shopapp.di

import android.app.Application
import com.example.shopapp.BuildConfig
import com.example.shopapp.ShopApp
import com.example.shopapp.common.data.LocalDatabase
import com.example.shopapp.common.domain.use_case.GetCartByProductIdUseCase
import com.example.shopapp.feature_store.data.ProductApi
import com.example.shopapp.feature_store.data.repository.CartRepositoryImpl
import com.example.shopapp.feature_store.data.repository.ProductRepositoryImpl
import com.example.shopapp.feature_store.domain.repository.CartRepository
import com.example.shopapp.feature_store.domain.repository.ProductRepository
import com.example.shopapp.feature_store.domain.useCase.cart.CartUseCase
import com.example.shopapp.feature_store.domain.useCase.cart.DeleteCartByIdUseCase
import com.example.shopapp.feature_store.domain.useCase.cart.GetAllCartsUseCase
import com.example.shopapp.common.domain.use_case.InsertOrUpdateCartUseCase
import com.example.shopapp.feature_auth.domain.repository.AuthRepository
import com.example.shopapp.feature_auth.domain.use_case.LogOutUseCase
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
    fun provideProductUseCase(
        application: Application,
        productRepository: ProductRepository,
        cartRepository: CartRepository,
        authRepository: AuthRepository
    ): ProductUseCase
    {
        return ProductUseCase(
            getProductsUseCase = GetProductsUseCase(productRepository=productRepository),
            refreshRemoteDataUseCase = RefreshRemoteDataUseCase(productRepository=productRepository),
            getCartByProductIdUseCase = GetCartByProductIdUseCase(cartRepository = cartRepository),
            insertOrUpdateCartUseCase = InsertOrUpdateCartUseCase(cartRepository= cartRepository),
            logOutUseCase = LogOutUseCase(application = application,authRepository)
        )
    }

    @Provides
    @ViewModelScoped
    fun provideCartRepository(localDatabase: LocalDatabase): CartRepository {
        return CartRepositoryImpl(localDatabase.cartDao)
    }

    @Provides
    @ViewModelScoped
    fun provideCartUseCase(cartRepository: CartRepository): CartUseCase
    {
        return CartUseCase(
            getAllCartsUseCase= GetAllCartsUseCase(cartRepository = cartRepository),
            insertOrUpdateCartUseCase= InsertOrUpdateCartUseCase(cartRepository=cartRepository),
            deleteCartByIdUseCase= DeleteCartByIdUseCase(cartRepository=cartRepository),
        )
    }
}