package com.example.shopapp.feature_store.data

import androidx.room.*
import com.example.shopapp.feature_store.data.dto.ProductWithCart
import com.example.shopapp.feature_store.data.entity.CartEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cartEntity: CartEntity)
    @Update
    suspend fun update(cartEntity: CartEntity)
    @Query("DELETE FROM cartentity where id=:id")
    suspend fun delete(id:Long)

    @Query("SELECT * FROM CartEntity where productId=:id")
    fun getCartByProductId(id:Long):Flow<CartEntity?>

    @Transaction
    @Query("SELECT * FROM cartentity")
    fun getAllCartItems(): Flow<List<ProductWithCart>>
}