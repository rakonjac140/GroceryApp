package com.appcrafters.grocery.base.data

import com.appcrafters.grocery.base.functional.Either
import com.appcrafters.grocery.base.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

interface IProductDataSource {
    suspend fun getAllProducts(): Either<Result>
}

class ProductDataSource(private val apiService: ProductApiService) : IProductDataSource {
    companion object {
        private const val host = "tasty.p.rapidapi.com"
        private const val key = "ea9d745207msh8cd03420e084d49p1ac50ajsn5f5967a56706"
    }

    override suspend fun getAllProducts(): Either<Result> = handleCall(apiService.getAllProducts(host,key))

    private suspend fun <T> handleCall(call: Call<T>): Either<T> {
        return withContext(Dispatchers.IO) {
            val response = call.execute()

            if (response.isSuccessful) {
                Either.Success(response.body()!!)
            } else {
                Either.Error(Exception(response.message()))
            }
        }
    }
}