package com.alesh.currencyconverter.ui.currencies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alesh.currencyconverter.App
import com.alesh.currencyconverter.R
import com.alesh.currencyconverter.common.base.BaseFragment
import com.alesh.currencyconverter.util.error.message
import com.alesh.currencyconverter.util.viewModel
import com.heliskycargo.utils.livedata.EventObserver
import kotlinx.android.synthetic.main.fragment_currencies.*

class CurrenciesFragment : BaseFragment() {

    private val viewModel: CurrenciesViewModel by viewModel {
        App.component.currenciesViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        observeViewModel()
        return inflater.inflate(R.layout.fragment_currencies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupData()
        setupButtons()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.currencies.removeObservers(viewLifecycleOwner)
        viewModel.error.removeObservers(viewLifecycleOwner)
    }

    private fun setupData() {
        viewModel.getCurrencies()
    }

    private fun setupButtons() {
        btnSignIn.setOnClickListener(this)
        btnSignUp.setOnClickListener(this)
        tvForgotPassword.setOnClickListener(this)
    }

    private fun observeViewModel() {

        viewModel.currencies.observe(
            viewLifecycleOwner,
            EventObserver {

            })

        viewModel.error.observe(
            viewLifecycleOwner,
            EventObserver {
                snackbar(container, it.message())
            })
    }
}

