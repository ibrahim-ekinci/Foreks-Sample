package com.gloorystudio.foreks_sample.data.repository

import com.gloorystudio.foreks_sample.data.local.db.service.CurrencyDao
import com.gloorystudio.foreks_sample.data.remote.CurrencyApi
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
    private val api: CurrencyApi,
    private val dao: CurrencyDao
) {

}