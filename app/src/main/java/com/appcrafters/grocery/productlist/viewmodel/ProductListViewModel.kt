package com.appcrafters.grocery.productlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appcrafters.grocery.base.data.IProductDataSource
import com.appcrafters.grocery.base.functional.Either
import com.appcrafters.grocery.productlist.view.ProductListViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductListViewModel(private val dataSource: IProductDataSource) : ViewModel() {

    private val _state = MutableLiveData<ProductListViewState>()
    val state: LiveData<ProductListViewState>
        get() = _state

    fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {

            _state.postValue(ProductListViewState.Proccessing)

            _state.postValue(
                when (val result = dataSource.getAllProducts()) {
                    is Either.Success -> ProductListViewState.DataReceived(result.data)
                    is Either.Error -> ProductListViewState.ErrorReceived(result.exception.toString())
                }
            )
        }
    }
}