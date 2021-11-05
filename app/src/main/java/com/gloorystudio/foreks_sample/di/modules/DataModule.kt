package com.gloorystudio.foreks_sample.di.modules

import android.app.Application
import com.gloorystudio.foreks_sample.data.local.db.service.CurrencyDao
import com.gloorystudio.foreks_sample.data.local.db.service.CurrencyDatabase
import com.gloorystudio.foreks_sample.data.remote.CurrencyApi
import com.gloorystudio.foreks_sample.data.repository.CurrencyRepository
import com.gloorystudio.foreks_sample.util.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object DataModule {

    @Singleton
    @Provides
    fun provideCurrencyRepository(
        api: CurrencyApi,
        dao: CurrencyDao
    ) = CurrencyRepository(api,dao)

    @Singleton
    @Provides
    fun provideCurrencyApi(): CurrencyApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(CurrencyApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCurrencyDatabase(context: Application): CurrencyDatabase {
        return CurrencyDatabase.invoke(context)
    }

    @Singleton
    @Provides
    fun provideCurrencyDao(currencyDatabase: CurrencyDatabase): CurrencyDao {
        return currencyDatabase.currencyDao()
    }
}