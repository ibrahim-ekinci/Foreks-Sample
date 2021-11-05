package com.gloorystudio.foreks_sample.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

open class BaseViewModel : ViewModel() {
    private val disposable = CompositeDisposable()
    var loadError = MutableLiveData("")
    var isLoading = MutableLiveData(false)

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    open fun <T> addDisposable(
        d: Single<T>,
        onSuccess: (t: T) -> Unit
    ) {
        disposable.add(
            d
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<T>() {
                    override fun onSuccess(t: T) {
                        loadError.value = ""
                        isLoading.value = false
                        onSuccess.invoke(t)
                    }

                    override fun onError(e: Throwable) {
                        loadError.value = e.message
                        isLoading.value = false
                    }
                })
        )
    }
}