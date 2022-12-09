package com.example.shopapp.feature_store.data.repository

import com.example.shopapp.common.utils.Resource
import com.example.shopapp.feature_auth.data.dto.LoginRequest
import com.example.shopapp.feature_auth.data.model.TokenEntity
import com.example.shopapp.feature_store.data.ProductApi
import com.example.shopapp.feature_store.data.ProductDao
import com.example.shopapp.feature_store.data.dto.toDomain
import com.example.shopapp.feature_store.data.entity.ProductEntity
import com.example.shopapp.feature_store.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException

class ProductRepositoryImpl(
    private val productApi:ProductApi,
    private val productDao: ProductDao
):ProductRepository {
    override fun getProducts(): Flow<List<ProductEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshRemoteData():Resource<Unit> {
        try {
            val res=productApi.getProducts()//.map { it.toDomain() }
            if(res.isSuccessful) {
                res.body()?.let{ list ->
                    productDao.insertAllProducts(list.map { it.toDomain() })

                    return Resource.Success(Unit)
                }
                return Resource.Error("No token found!!")
            }else{
                return Resource.Error(message = "Login Failed: ${if (res.code()==401) "Unauthorized" else res.code()}")
            }
        }catch (e: IOException) {
            return Resource.Error( message = "Could not reach the server, please check your internet connection!")
        } catch (e: HttpException) {
            return Resource.Error(message = "Oops, something went wrong!")
        }


    }
}