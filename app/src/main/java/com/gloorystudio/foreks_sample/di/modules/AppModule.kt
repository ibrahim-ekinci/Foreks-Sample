package com.gloorystudio.foreks_sample.di.modules

import android.app.Application
import com.gloorystudio.foreks_sample.data.local.db.service.CurrencyDao
import com.gloorystudio.foreks_sample.data.local.db.service.CurrencyDatabase
import com.gloorystudio.foreks_sample.data.remote.CurrencyApi
import com.gloorystudio.foreks_sample.data.repository.CurrencyRepository
import com.gloorystudio.foreks_sample.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(
    includes = [
        ViewModelModule::class,
        DataModule::class
    ]
)
object AppModule {

}