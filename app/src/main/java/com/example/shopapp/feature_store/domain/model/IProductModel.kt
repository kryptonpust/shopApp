package com.example.shopapp.feature_store.domain.model

interface IProductModel {
    val id:Long
    val title:String
    val price:Float
    val category:String
    val description:String
    val image:String
}