package com.example.shopapp.feature_auth.domain.model

interface ITokenModel {
    val token:String
    val remember:Boolean
}