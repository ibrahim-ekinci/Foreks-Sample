package com.gloorystudio.foreks_sample.ui.currency_detail

import androidx.fragment.app.viewModels
import com.gloorystudio.foreks_sample.R
import com.gloorystudio.foreks_sample.base.BaseFragment
import com.gloorystudio.foreks_sample.databinding.FragmentCurrencyDetailBinding

class CurrencyDetailFragment :
    BaseFragment<FragmentCurrencyDetailBinding>(R.layout.fragment_currency_detail) {

    private val viewModel: CurrencyDetailViewModel by viewModels {
        viewModelFactory
    }

    override fun initUi() {
    }

    override fun observeData() {

    }
}
