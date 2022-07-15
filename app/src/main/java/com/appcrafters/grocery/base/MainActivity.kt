package com.appcrafters.grocery.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.appcrafters.grocery.R
import com.appcrafters.grocery.base.model.Product
import com.appcrafters.grocery.productlist.view.ProductListFragment
import com.appcrafters.grocery.productdetails.view.ProductDetailsFragment

class MainActivity : AppCompatActivity(), ICoordinator {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        showFragment(ProductListFragment(), true)
    }

    private fun showFragment(fragment: Fragment, addAsRoot: Boolean = false) {

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        if (!addAsRoot) transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun showDetailsFragment(product: Product) {
        showFragment(ProductDetailsFragment.newInstance(product))
    }

}