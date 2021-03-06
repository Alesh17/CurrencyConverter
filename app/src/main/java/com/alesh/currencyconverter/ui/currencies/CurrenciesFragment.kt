package com.alesh.currencyconverter.ui.currencies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alesh.currencyconverter.App
import com.alesh.currencyconverter.R
import com.alesh.currencyconverter.common.base.BaseFragment
import com.alesh.currencyconverter.ui.currencies.adapter.CurrenciesAdapter
import com.alesh.currencyconverter.util.dagger.viewModel
import com.alesh.currencyconverter.util.decoration.LinearLayoutDecoration
import com.alesh.currencyconverter.util.error.message
import com.alesh.currencyconverter.util.livedata.EventObserver
import kotlinx.android.synthetic.main.fragment_currencies.*

class CurrenciesFragment : BaseFragment(), View.OnClickListener {

    private val adapter by lazy { CurrenciesAdapter(viewModel::calculate) }
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
        setupRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fab.setOnClickListener(null)
        viewModel.favoriteCurrencies.removeObservers(viewLifecycleOwner)
        viewModel.error.removeObservers(viewLifecycleOwner)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.fab -> {
                navigate(R.id.action_currenciesFragment_to_settingsFragment)
            }
        }
    }

    private fun setupData() {
        viewModel.getFavoriteCurrencies()
    }

    private fun setupButtons() {
        fab.setOnClickListener(this)
    }

    private fun setupRecyclerView() {
        val margin = resources.getDimensionPixelSize(R.dimen.recycler_item_margin)
        rvFavoriteCurrencies.adapter = adapter
        rvFavoriteCurrencies.addItemDecoration(LinearLayoutDecoration(margin))
    }

    private fun observeViewModel() {

        viewModel.favoriteCurrencies.observe(
            viewLifecycleOwner,
            EventObserver {
                adapter.submitList(it)
            })

        viewModel.error.observe(
            viewLifecycleOwner,
            EventObserver {
                snackbar(container, it.message())
            })

        viewModel.isLoading.observe(
            viewLifecycleOwner,
            EventObserver {
                showLoading(it)
            })
    }
}

