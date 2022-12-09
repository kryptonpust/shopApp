package com.example.shopapp.common.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shopapp.feature_auth.data.AuthDao
import com.example.shopapp.feature_auth.data.model.TokenEntity
import com.example.shopapp.feature_store.data.ProductDao
import com.example.shopapp.feature_store.data.entity.ProductEntity

@Database(entities = [TokenEntity::class,ProductEntity::class], version = 2)
abstract class LocalDatabase:RoomDatabase() {
    abstract val authDao: AuthDao
    abstract val productDao: ProductDao

}