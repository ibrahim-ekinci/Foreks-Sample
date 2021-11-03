package com.gloorystudio.foreks_sample.ui.currency_list

import androidx.lifecycle.ViewModel
import com.gloorystudio.foreks_sample.data.repository.CurrencyRepository
import javax.inject.Inject

class CurrencyListViewModel @Inject constructor(private val repository: CurrencyRepository):
    ViewModel() {
}