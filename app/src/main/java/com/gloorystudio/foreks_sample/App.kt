package com.gloorystudio.foreks_sample

import com.gloorystudio.foreks_sample.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {
    private val applicationInjector = DaggerAppComponent.builder().application(this).build()
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = applicationInjector
}