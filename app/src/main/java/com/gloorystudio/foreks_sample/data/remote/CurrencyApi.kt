package com.gloorystudio.foreks_sample.data.remote

import com.gloorystudio.foreks_sample.data.remote.responses.CurrencyDetail
import com.gloorystudio.foreks_sample.data.remote.responses.CurrencyGraph
import com.gloorystudio.foreks_sample.data.remote.responses.CurrencyList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("coin/list.php")
    fun getCurrencyList(): Single<CurrencyList>

    @GET("coin/detail.php")
    fun getCurrencyDetail(
        @Query("cod") code:String
    ): Single<CurrencyDetail>

    @GET("coin/graph.php")
    fun getCurrencyGraph(
        @Query("cod") code:String
    ): Single<CurrencyGraph>
}