package com.gloorystudio.foreks_sample.di.modules

import com.gloorystudio.foreks_sample.ui.currency_detail.CurrencyDetailFragment
import com.gloorystudio.foreks_sample.ui.currency_fav_list.CurrencyFavoriteListFragment
import com.gloorystudio.foreks_sample.ui.currency_list.CurrencyListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeCurrencyListFragment(): CurrencyListFragment

    @ContributesAndroidInjector
    abstract fun contributeCurrencyFavoriteListFragment(): CurrencyFavoriteListFragment

    @ContributesAndroidInjector
    abstract fun contributeCurrencyDetailFragment(): CurrencyDetailFragment
}