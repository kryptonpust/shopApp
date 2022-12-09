package com.example.shopapp.feature_auth.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shopapp.feature_auth.domain.model.ITokenModel

@Entity
data class TokenEntity (
    @PrimaryKey(autoGenerate = true) val id:Long?=null,
    override val token:String,
    override val remember: Boolean
): ITokenModel