package com.appcrafters.grocery.base.productlist.viewmodel

import androidx.lifecycle.Observer
import com.appcrafters.grocery.base.InstantExecutorTest
import com.appcrafters.grocery.base.data.IProductDataSource
import com.appcrafters.grocery.base.functional.Either
import com.appcrafters.grocery.base.model.Result
import com.appcrafters.grocery.productlist.view.ProductListViewState
import com.appcrafters.grocery.productlist.view.ProductListViewState.*
import com.appcrafters.grocery.productlist.viewmodel.ProductListViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.openMocks

class ProductListViewModelTests : InstantExecutorTest() {
    @Mock
    lateinit var dataSource: IProductDataSource

    @Mock
    lateinit var stateObserver: Observer<ProductListViewState>

    lateinit var viewModel: ProductListViewModel

    @Before
    fun setUp() {
        openMocks(this)
        viewModel = ProductListViewModel(dataSource)
        viewModel.state.observeForever(stateObserver)
    }

    @Test
    fun `testGetProducts, has result, state changed to Proccessing - DataReceived`() = runBlocking {
        val expectedResult: Result = Result()

        `when`(dataSource.getAllProducts()).thenReturn(Either.Success(expectedResult))

        viewModel.getProducts()

        verify(stateObserver).onChanged(Proccessing)
        verify(stateObserver).onChanged(DataReceived(expectedResult))
    }

    @Test
    fun `testGetProducts, has error, state changed to Proccessing - ErrorReceived`() = runBlocking {
        val expectedError = Exception("test")

        `when`(dataSource.getAllProducts()).thenReturn(Either.Error(expectedError))

        viewModel.getProducts()

        verify(stateObserver).onChanged(ErrorReceived(expectedError.toString()))
    }
}