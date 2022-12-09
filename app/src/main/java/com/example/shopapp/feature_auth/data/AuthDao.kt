package com.example.shopapp.feature_auth.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.shopapp.feature_auth.data.model.TokenEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AuthDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertToken(token: TokenEntity)

    @Query("DELETE FROM TokenEntity")
    suspend fun deleteToken()

    @Query("SELECT * FROM TokenEntity LIMIT 1")
    fun getTokenFlow(): Flow<TokenEntity?>

    @Query("SELECT * FROM TokenEntity LIMIT 1")
    fun getToken(): TokenEntity?
}