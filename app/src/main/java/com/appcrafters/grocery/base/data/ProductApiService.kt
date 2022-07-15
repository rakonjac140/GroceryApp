package com.appcrafters.grocery.base.data

import com.appcrafters.grocery.base.model.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ProductApiService {

    @GET("list?from=0&size=30")
    fun getAllProducts(@Header("x-rapidapi-host") host: String, @Header("x-rapidapi-key") key: String): Call<Result>
}