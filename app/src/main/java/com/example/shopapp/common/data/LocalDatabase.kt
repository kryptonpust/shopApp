package com.example.shopapp.common.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shopapp.feature_auth.data.AuthDao
import com.example.shopapp.feature_auth.data.model.TokenEntity

@Database(entities = [TokenEntity::class], version = 1)
abstract class LocalDatabase:RoomDatabase() {
    abstract val authDao: AuthDao

}