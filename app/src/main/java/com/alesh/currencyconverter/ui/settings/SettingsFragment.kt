package com.alesh.currencyconverter.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alesh.currencyconverter.App
import com.alesh.currencyconverter.R
import com.alesh.currencyconverter.common.base.BaseFragment
import com.alesh.currencyconverter.ui.settings.adapter.SettingsAdapter
import com.alesh.currencyconverter.util.dagger.viewModel
import com.alesh.currencyconverter.util.decoration.LinearLayoutDecoration
import com.alesh.currencyconverter.util.error.message
import com.alesh.currencyconverter.util.livedata.EventObserver
import kotlinx.android.synthetic.main.fragment_currencies.container
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : BaseFragment(), View.OnClickListener {

    private val adapter by lazy {
        SettingsAdapter(
            viewModel::addToFavorites,
            viewModel::removeFromFavorites
        )
    }

    private val viewModel: SettingsViewModel by viewModel {
        App.component.settingsViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        observeViewModel()
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupData()
        setupButtons()
        setupRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        btnSave.setOnClickListener(this)
        viewModel.currencies.removeObservers(viewLifecycleOwner)
        viewModel.error.removeObservers(viewLifecycleOwner)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btnSave -> {
                navigate(R.id.action_settingsFragment_to_currenciesFragment)
            }
        }
    }

    private fun setupData() {
        viewModel.getCurrencies()
    }

    private fun setupButtons() {
        btnSave.setOnClickListener(this)
    }

    private fun setupRecyclerView() {
        val margin = resources.getDimensionPixelSize(R.dimen.recycler_item_margin)
        rvSettingsCurrencies.adapter = adapter
        rvSettingsCurrencies.addItemDecoration(LinearLayoutDecoration(margin))
    }

    private fun observeViewModel() {

        viewModel.currencies.observe(
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
