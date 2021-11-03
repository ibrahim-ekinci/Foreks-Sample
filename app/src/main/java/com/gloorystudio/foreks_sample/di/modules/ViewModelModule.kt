package com.gloorystudio.foreks_sample.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gloorystudio.foreks_sample.ui.currency_detail.CurrencyDetailViewModel
import com.gloorystudio.foreks_sample.ui.currency_fav_list.CurrencyFavoriteListViewModel
import com.gloorystudio.foreks_sample.ui.currency_list.CurrencyListViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CurrencyListViewModel::class)
    abstract fun bindCurrencyListViewModel(currencyListViewModel: CurrencyListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CurrencyFavoriteListViewModel::class)
    abstract fun bindCurrencyFavoriteListViewModel(currencyFavoriteListViewModel: CurrencyFavoriteListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CurrencyDetailViewModel::class)
    abstract fun bindCurrencyDetailViewModel(currencyDetailViewModel: CurrencyDetailViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Singleton
class ViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
        return creator.get() as T
    }
}