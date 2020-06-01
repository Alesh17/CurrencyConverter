package com.alesh.currencyconverter.common.base

import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.alesh.currencyconverter.util.view.hideKeyboard
import com.alesh.currencyconverter.util.view.snackbar

open class BaseFragment : Fragment() {

    override fun onStop() {
        super.onStop()
        hideKeyboard()
    }

    /* Messages */
    fun snackbar(view: View, @StringRes messageStringRes: Int) {
        requireContext().snackbar(view, messageStringRes)
    }

    /* Navigation */
    fun navigate(@IdRes resId: Int) {
        findNavController().navigate(resId)
    }

    fun navigateBack() {
        findNavController().popBackStack()
    }

    /* Other */
    private fun hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }
}