package com.example.shopapp.feature_store.data

import androidx.room.*
import com.example.shopapp.feature_store.data.dto.ProductWithCart
import com.example.shopapp.feature_store.data.entity.Cart
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cart: Cart)
    @Update
    suspend fun update(cart: Cart)
    @Query("DELETE FROM cart where id=:id")
    suspend fun delete(id:Long)

    @Query("SELECT * FROM Cart where productId=:id")
    fun getCartByProductId(id:Long):Flow<Cart?>

    @Transaction
    @Query("SELECT * FROM cart")
    fun getAllCartItems(): Flow<List<ProductWithCart>>
}