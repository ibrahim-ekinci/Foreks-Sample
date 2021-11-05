package com.gloorystudio.foreks_sample.ui.currency_list

import androidx.lifecycle.MutableLiveData
import com.gloorystudio.foreks_sample.base.BaseViewModel
import com.gloorystudio.foreks_sample.data.models.CurrencyEntry
import com.gloorystudio.foreks_sample.data.remote.responses.CurrencyList
import com.gloorystudio.foreks_sample.data.repository.CurrencyRepository
import com.gloorystudio.foreks_sample.util.Resource
import javax.inject.Inject

class CurrencyListViewModel @Inject constructor(private val repository: CurrencyRepository) :
    BaseViewModel() {

    var currencyList = MutableLiveData<List<CurrencyEntry>>(listOf())

    init {
        fetchCurrencyList()
    }

    fun fetchCurrencyList() {
        when (val result = repository.getCurrencyList()) {
            is Resource.Success -> {
                result.data?.let {
                    addDisposable(it) {cList->
                        val currencyEntryList = cList.l.mapIndexed { _, entry ->
                            CurrencyEntry(
                                code = entry.cod,
                                description = entry.def,
                                id = entry.tke,
                                clock = entry.clo,
                                last = entry.las,
                                pDifference = entry.pdd,
                                dLow = entry.low,
                                dHigh = entry.hig,
                                buy = entry.buy,
                                difference = entry.ddi,
                                sell = entry.sel,
                                pdClose = entry.pdc,
                                isRealTimeData = entry.rtp,
                                cl3 = entry.cl3
                            )
                        }
                        currencyList.value = currencyEntryList
                    }
                }
            }
            is Resource.Error -> {
                loadError.value = result.message!!
                isLoading.value = false
            }
        }
    }
}