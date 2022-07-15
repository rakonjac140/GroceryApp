package com.appcrafters.grocery.productlist.view

import com.appcrafters.grocery.base.model.Result

sealed class ProductListViewState {

    object Proccessing : ProductListViewState()
    data class DataReceived(val products: Result) : ProductListViewState()
    data class ErrorReceived(val message: String) : ProductListViewState()
}