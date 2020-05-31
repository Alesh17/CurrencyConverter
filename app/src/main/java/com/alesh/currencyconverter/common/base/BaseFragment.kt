package com.alesh.currencyconverter.common.base

import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.alesh.currencyconverter.util.view.hideKeyboard
import com.alesh.currencyconverter.util.view.snackbar
import com.alesh.currencyconverter.util.view.toast

open class BaseFragment : Fragment() {

    override fun onStop() {
        super.onStop()
        hideKeyboard()
    }

    /* Messages */
    fun toast(@StringRes messageStringRes: Int) {
        requireContext().toast(messageStringRes)
    }

    fun snackbar(view: View, @StringRes messageStringRes: Int) {
        requireContext().snackbar(view, messageStringRes)
    }

    /* Navigation */
    fun navigate(@IdRes resId: Int) {
        findNavController().navigate(resId)
    }

    fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }

    fun navigateBack() {
        findNavController().popBackStack()
    }

    /* Other */
    fun hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }
}