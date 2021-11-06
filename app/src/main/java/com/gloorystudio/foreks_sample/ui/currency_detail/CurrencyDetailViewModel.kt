package com.gloorystudio.foreks_sample.ui.currency_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gloorystudio.foreks_sample.base.BaseViewModel
import com.gloorystudio.foreks_sample.data.local.db.entity.CurrencyFavoriteEntity
import com.gloorystudio.foreks_sample.data.models.CurrencyDetailEntry
import com.gloorystudio.foreks_sample.data.remote.responses.CurrencyGraph
import com.gloorystudio.foreks_sample.data.repository.CurrencyRepository
import com.gloorystudio.foreks_sample.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrencyDetailViewModel @Inject constructor(private val repository: CurrencyRepository) :
    BaseViewModel() {

    var currencyDetail = MutableLiveData<CurrencyDetailEntry>()
    var currencyGraph = MutableLiveData<CurrencyGraph>()

    fun fetchCurrencyDetail(code: String) {
        when (val result = repository.getCurrencyDetail(code)) {
            is Resource.Success -> {
                result.data?.let {
                    addDisposable(it) { cDetail ->
                        val field = cDetail.d?.firstOrNull()?.fields
                        if (field !=null){
                            currencyDetail.value = CurrencyDetailEntry(
                                code,
                                cDetail.d.first().clo,
                                field.buy,
                                field.ddi,
                                field.hig,
                                field.las,
                                field.low,
                                field.pdd,
                                field.sel
                            )
                        }else{
                            loadError.value = "Fields are null."
                        }
                    }
                }
            }
            is Resource.Error -> {
                loadError.value = result.message!!
                isLoading.value = false
            }
        }
    }

    fun fetchCurrencyGraph(code:String){
        when (val result = repository.getCurrencyGraph(code)) {
            is Resource.Success -> {
                result.data?.let {
                    addDisposable(it) { cGraph ->
                        currencyGraph.value = cGraph
                    }
                }
            }
            is Resource.Error -> {
                loadError.value = result.message!!
                isLoading.value = false
            }
        }
    }

    fun getFavoriteCurrency(code:String, isLiked: (Boolean)->Unit){
        viewModelScope.launch {
            isLiked.invoke(repository.isCurrencyFavoriteToDb(code))
        }
    }

    fun addCurrencyFavorite(){
        currencyDetail.value?.let { currencyDetailEntry ->
            viewModelScope.launch {
                repository.addCurrencyFavoriteToDb(
                    CurrencyFavoriteEntity(code = currencyDetailEntry.code)
                )
            }
        }
    }

    fun removeFavoriteCurrency(code:String){
        viewModelScope.launch {
            repository.deleteCurrencyFavoriteToDb(code)
        }
    }
}