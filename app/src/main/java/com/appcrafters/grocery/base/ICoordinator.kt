package com.appcrafters.grocery.base

import com.appcrafters.grocery.base.model.Product

interface ICoordinator {
    fun showDetailsFragment(product: Product)
}