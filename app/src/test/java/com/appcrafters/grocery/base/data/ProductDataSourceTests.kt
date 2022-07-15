package com.appcrafters.grocery.base.data

import com.appcrafters.grocery.base.model.Result
import com.appcrafters.grocery.base.functional.Either
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.openMocks
import retrofit2.Call
import retrofit2.Response

class ProductDataSourceTests {

    @Mock
    lateinit var apiService: ProductApiService

    @Mock
    lateinit var getProductsCall: Call<Result>

    lateinit var dataSource: ProductDataSource

    @Before
    fun setUp() {
        openMocks(this)
        dataSource = ProductDataSource(apiService)
    }

    @Test
    fun `testGetProducts, has response, Success returned`() = runBlocking {
        val expectedProducts: Result = Result()
        val expectedResult = Either.Success(expectedProducts)

        `when`(apiService.getAllProducts(anyString(), anyString())).thenReturn(getProductsCall)
        `when`(getProductsCall.execute()).thenReturn(Response.success(expectedProducts))

        val result = dataSource.getAllProducts()

        assertEquals(expectedResult, result)
    }

    @Test
    fun `testGetProducts, has error, Error returned`() = runBlocking {
        val expectedResponseBody = ResponseBody.create(
            MediaType.parse("application/json"), ""
        )

        `when`(apiService.getAllProducts(anyString(), anyString())).thenReturn(getProductsCall)
        `when`(getProductsCall.execute()).thenReturn(Response.error(400, expectedResponseBody))


        val result = dataSource.getAllProducts()

        assertTrue(result is Either.Error)
    }
}