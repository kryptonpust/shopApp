package com.example.shopapp.di

import android.app.Application
import com.example.shopapp.ShopApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

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
}