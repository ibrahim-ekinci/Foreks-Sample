package com.gloorystudio.foreks_sample.ui.currency_fav_list

import androidx.fragment.app.viewModels
import com.gloorystudio.foreks_sample.R
import com.gloorystudio.foreks_sample.base.BaseFragment
import com.gloorystudio.foreks_sample.databinding.FragmentCurrencyFavoriteListBinding

class CurrencyFavoriteListFragment :
    BaseFragment<FragmentCurrencyFavoriteListBinding>(R.layout.fragment_currency_favorite_list) {

    private val viewModel: CurrencyFavoriteListViewModel by viewModels {
        viewModelFactory
    }

    override fun initUi() {
    }

    override fun observeData() {

    }
}
