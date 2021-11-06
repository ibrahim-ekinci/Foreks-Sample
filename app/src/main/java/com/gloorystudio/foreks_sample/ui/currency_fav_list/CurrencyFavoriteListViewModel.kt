package com.gloorystudio.foreks_sample.ui.currency_fav_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gloorystudio.foreks_sample.base.BaseViewModel
import com.gloorystudio.foreks_sample.data.local.db.entity.CurrencyFavoriteEntity
import com.gloorystudio.foreks_sample.data.models.CurrencyEntry
import com.gloorystudio.foreks_sample.data.repository.CurrencyRepository
import com.gloorystudio.foreks_sample.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrencyFavoriteListViewModel @Inject constructor(private val repository: CurrencyRepository) :
    BaseViewModel() {

    var currencyList = MutableLiveData<List<CurrencyEntry>>(listOf())

    init {
        fetchCurrencyList()
    }

    fun fetchCurrencyList() {
        when (val result = repository.getCurrencyList()) {
            is Resource.Success -> {
                result.data?.let {
                    addDisposable(it) { cList ->
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
                        filterFavorites(currencyEntryList)
                    }
                }
            }
            is Resource.Error -> {
                loadError.value = result.message!!
                isLoading.value = false
            }
        }
    }

    private fun filterFavorites(currencyEntryList: List<CurrencyEntry>) {
        viewModelScope.launch {
            repository.getAllFavoriteCurrencyToDb()?.let { favCurrencyList ->
                currencyList.value = currencyEntryList.filter {
                    favCurrencyList.contains(
                        CurrencyFavoriteEntity(it.code)
                    )
                }
            }
        }
    }
}