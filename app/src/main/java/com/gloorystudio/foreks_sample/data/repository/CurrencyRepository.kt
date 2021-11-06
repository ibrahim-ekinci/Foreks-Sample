package com.gloorystudio.foreks_sample.data.repository

import com.gloorystudio.foreks_sample.data.local.db.entity.CurrencyFavoriteEntity
import com.gloorystudio.foreks_sample.data.local.db.service.CurrencyDao
import com.gloorystudio.foreks_sample.data.remote.CurrencyApi
import com.gloorystudio.foreks_sample.data.remote.responses.CurrencyDetail
import com.gloorystudio.foreks_sample.data.remote.responses.CurrencyGraph
import com.gloorystudio.foreks_sample.data.remote.responses.CurrencyList
import com.gloorystudio.foreks_sample.util.Resource
import io.reactivex.Single
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
    private val api: CurrencyApi,
    private val dao: CurrencyDao
) {
    fun getCurrencyList(): Resource<Single<CurrencyList>>{
        val response = try {
            api.getCurrencyList()
        } catch (e: Exception) {
            return Resource.Error(
                e.message.toString()
            )
        }
        return Resource.Success(response)
    }

    fun getCurrencyDetail(code: String): Resource<Single<CurrencyDetail>>{
        val response = try {
            api.getCurrencyDetail(code)
        } catch (e: Exception) {
            return Resource.Error(
                e.message.toString()
            )
        }
        return Resource.Success(response)
    }

    fun getCurrencyGraph(code: String): Resource<Single<CurrencyGraph>>{
        val response = try {
            api.getCurrencyGraph(code)
        } catch (e: Exception) {
            return Resource.Error(
                e.message.toString()
            )
        }
        return Resource.Success(response)
    }

    suspend fun addCurrencyFavoriteToDb(currencyFavoriteEntity: CurrencyFavoriteEntity){
        dao.insertCurrencyFavorite(currencyFavoriteEntity)
    }

    suspend fun deleteCurrencyFavoriteToDb(code: String){
        dao.deleteCurrencyFavorite(code)
    }

    suspend fun isCurrencyFavoriteToDb(code: String): Boolean{
        return dao.getCurrencyFavorite(code)!=null
    }

    suspend fun getAllFavoriteCurrencyToDb(): List<CurrencyFavoriteEntity>? {
        return dao.getAllCurrencyFavorite()
    }
}