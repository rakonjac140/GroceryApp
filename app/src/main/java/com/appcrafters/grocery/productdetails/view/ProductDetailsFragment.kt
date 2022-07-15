package com.appcrafters.grocery.productdetails.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.appcrafters.grocery.base.model.Product
import com.appcrafters.grocery.R
import com.appcrafters.grocery.base.data.ApiServiceProvider
import com.appcrafters.grocery.base.data.ProductDataSource
import com.appcrafters.grocery.base.functional.ViewModelFactoryUtil
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_product_details.*
import java.util.*

class ProductDetailsFragment : Fragment() {

    companion object {

        private var PRODUCT: Product = Product()

        fun newInstance(product: Product): ProductDetailsFragment {

            return ProductDetailsFragment().apply {
                arguments = Bundle().apply {
                    PRODUCT = product
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindFromViewModel(PRODUCT)
    }

    private fun bindFromViewModel(product: Product) {
        setUpView(product)
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun setUpView(product: Product) {
        Glide.with(this).load(product.thumbnail_url).into(groceryImg)
        groceryNameTxt.text = product.name

        if (product.description == null || product.description == "")
            groceryDescTxt.text = "No description available"
        else
            groceryDescTxt.text = product.description

        var instr: String = ""
        product.instructions.forEach {
            instr += it.display_text + "\n"
        }

        if(instr == "")
            instructions.text = "No instructions available"
        else
            instructions.text = instr

        if (product.cook_time_minutes == 0)
            cookTxt.text = "No cooking data available!"
        else
            cookTxt.text = "You have to cook it for " + product.cook_time_minutes + " minutes"
    }
}