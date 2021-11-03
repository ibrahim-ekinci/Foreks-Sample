package com.gloorystudio.foreks_sample.ui.currency_fav_list

import androidx.lifecycle.ViewModel
import com.gloorystudio.foreks_sample.data.repository.CurrencyRepository
import javax.inject.Inject

class CurrencyFavoriteListViewModel @Inject constructor(private val repository: CurrencyRepository):
    ViewModel() {
}