package com.alesh.currencyconverter.common.base

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.alesh.currencyconverter.util.view.buildLoadingDialog
import com.alesh.currencyconverter.util.view.hideKeyboard
import com.alesh.currencyconverter.util.view.onBackPressedListener
import com.alesh.currencyconverter.util.view.snackbar

open class BaseFragment : Fragment() {

    private var loadingDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressedHandler()
    }

    override fun onStop() {
        super.onStop()
        hideKeyboard()
    }

    /* Loadings */
    fun showLoading(isLoading: Boolean) {
        loadingDialog = if (isLoading) buildLoadingDialog().apply { show() }
        else loadingDialog?.dismiss().let { null }
    }

    /* Messages */
    fun snackbar(view: View, @StringRes messageStringRes: Int) {
        requireContext().snackbar(view, messageStringRes)
    }

    /* Navigation */
    private fun onBackPressedHandler() {
        requireActivity().onBackPressedListener()
    }

    fun navigate(@IdRes resId: Int) {
        findNavController().navigate(resId)
    }

    /* Other */
    private fun hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }
}