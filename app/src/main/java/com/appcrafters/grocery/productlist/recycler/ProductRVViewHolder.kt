package com.appcrafters.grocery.productlist.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.appcrafters.grocery.base.model.Product
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_product.view.*;

class ProductRVViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(product: Product, onItemClicked: (Product) -> Unit) {
        Glide.with(itemView).load(product.thumbnail_url).into(itemView.productImg)

        itemView.productNameTxt.text = product.name
        if(product.description == null || product.description == "")
            itemView.productDescriptionTxt.text = "No description"
        else if (product.description.length > 90)
            itemView.productDescriptionTxt.text = product.description.substring(0,90) + "..."
        else
            itemView.productDescriptionTxt.text = product.description


        itemView.setOnClickListener {
            onItemClicked.invoke(product)
        }
    }
}