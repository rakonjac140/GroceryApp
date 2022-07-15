package com.appcrafters.grocery.base.data

object ApiServiceProvider {
    val productsApiService = RetrofitBuilder.retrofit.create(ProductApiService::class.java)
}