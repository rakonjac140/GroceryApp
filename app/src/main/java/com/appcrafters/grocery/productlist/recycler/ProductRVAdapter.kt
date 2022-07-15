package com.appcrafters.grocery.productlist.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.appcrafters.grocery.R
import com.appcrafters.grocery.base.model.Product
import com.appcrafters.grocery.base.model.Result

class ProductRVAdapter(private val products: Result, private val onItemClicked: (Product) -> Unit) :
    Adapter<ProductRVViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductRVViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
    )

    override fun onBindViewHolder(holder: ProductRVViewHolder, position: Int) {
        holder.bind(products.results[position], onItemClicked)
    }

    override fun getItemCount() = products.results.size
}