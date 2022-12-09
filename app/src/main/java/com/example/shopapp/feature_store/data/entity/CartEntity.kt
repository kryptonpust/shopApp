package com.example.shopapp.feature_store.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import javax.annotation.Nullable

@Entity(indices = [Index(value = ["productId"],unique=true)])
data class CartEntity(
    @PrimaryKey(autoGenerate = true) @Nullable val id:Long?,
    val productId:Long,
    val quantity: Long
)
