package com.gloorystudio.foreks_sample.ui.currency_list

import androidx.fragment.app.viewModels
import com.gloorystudio.foreks_sample.R
import com.gloorystudio.foreks_sample.base.BaseFragment
import com.gloorystudio.foreks_sample.databinding.FragmentCurrencyListBinding

class CurrencyListFragment :
    BaseFragment<FragmentCurrencyListBinding>(R.layout.fragment_currency_list) {

    private val viewModel: CurrencyListViewModel by viewModels {
        viewModelFactory
    }

    override fun initUi() {
    }

    override fun observeData() {

    }
}