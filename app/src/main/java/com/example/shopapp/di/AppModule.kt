package com.example.shopapp.di

import android.app.Application
import androidx.room.Room
import com.example.shopapp.BuildConfig
import com.example.shopapp.common.data.LocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
}