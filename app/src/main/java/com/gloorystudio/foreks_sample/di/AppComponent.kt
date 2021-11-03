package com.gloorystudio.foreks_sample.di

import android.app.Application
import com.gloorystudio.foreks_sample.App
import com.gloorystudio.foreks_sample.di.modules.AppModule
import com.gloorystudio.foreks_sample.di.modules.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        MainActivityModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: App)
}