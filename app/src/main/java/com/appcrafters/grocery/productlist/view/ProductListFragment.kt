package com.appcrafters.grocery.productlist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.appcrafters.grocery.R
import com.appcrafters.grocery.base.ICoordinator
import com.appcrafters.grocery.base.data.ApiServiceProvider
import com.appcrafters.grocery.base.data.ProductDataSource
import com.appcrafters.grocery.base.functional.ViewModelFactoryUtil
import com.appcrafters.grocery.base.model.Result
import com.appcrafters.grocery.productlist.recycler.ProductRVAdapter
import com.appcrafters.grocery.productlist.viewmodel.ProductListViewModel
import kotlinx.android.synthetic.main.fragment_product_list.*

class ProductListFragment : Fragment() {

    lateinit var viewModel: ProductListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, ViewModelFactoryUtil.viewModelFactory {
            ProductListViewModel(ProductDataSource(ApiServiceProvider.productsApiService))
        }).get(ProductListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindFromViewModel()
        viewModel.getProducts()
    }

    private fun setUpRecyclerView(products: Result) {
        productListRV.adapter = ProductRVAdapter(products) { product ->
            (activity as ICoordinator).showDetailsFragment(product)
        }
    }

    private fun bindFromViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->

            productListProgressBar.isVisible = state is ProductListViewState.Proccessing

            when (state) {
                is ProductListViewState.DataReceived -> { setUpRecyclerView(state.products) }
                is ProductListViewState.ErrorReceived -> showError(state.message)
            }
        }
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}