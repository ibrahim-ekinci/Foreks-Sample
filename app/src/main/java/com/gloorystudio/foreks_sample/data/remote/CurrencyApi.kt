package com.gloorystudio.foreks_sample.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("coin/list.php")
    fun getCurrencyList(): Single<CurrencyApi>
}