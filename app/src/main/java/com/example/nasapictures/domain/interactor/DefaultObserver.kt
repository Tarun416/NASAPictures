package com.example.nasapictures.domain.interactor

import io.reactivex.observers.DisposableObserver

open class DefaultObserver<T> : DisposableObserver<T>()
{
    override fun onComplete() {

    }

    override fun onNext(value: T) {

    }

    override fun onError(e: Throwable?) {

    }

}