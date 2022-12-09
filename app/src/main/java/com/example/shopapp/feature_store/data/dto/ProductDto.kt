package com.example.shopapp.feature_store.data.dto

import com.example.shopapp.feature_store.data.entity.ProductEntity


data class ProductDto(
    val id:Long,
    val title:String,
    val price:Float,
    val category:String,
    val description:String,
    val image:String
)
fun ProductDto.toDomain():ProductEntity{
    return ProductEntity(
        id=id,
        title=title,
        price=price,
        category=category,
        description=description,
        image=image
    )
}