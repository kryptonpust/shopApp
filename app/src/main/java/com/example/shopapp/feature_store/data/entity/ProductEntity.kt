package com.example.shopapp.feature_store.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shopapp.feature_store.domain.model.IProductModel


@Entity
data class ProductEntity(
    @PrimaryKey override val id:Long,
    override val title:String,
    override val price:Float,
    override val category:String,
    override val description:String,
    override val image:String
):IProductModel