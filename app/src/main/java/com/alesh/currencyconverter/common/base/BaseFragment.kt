package com.alesh.currencyconverter.common.base

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.alesh.currencyconverter.util.view.hideKeyboard
import com.alesh.currencyconverter.util.view.onBackPressedListener
import com.alesh.currencyconverter.util.view.snackbar

open class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressedHandler()
    }

    override fun onStop() {
        super.onStop()
        hideKeyboard()
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