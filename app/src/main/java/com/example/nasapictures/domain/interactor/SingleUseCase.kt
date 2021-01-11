package com.example.nasapictures.domain.interactor

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<T,Params>()
{

    private  var disposables = CompositeDisposable()


    abstract fun buildUseCaseObservable(params : Params?) : Observable<T>

    fun execute(observer : DisposableObserver<T>,params: Params?)
    {
       val observable =  this.buildUseCaseObservable(params)
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())

       addDisposable(observable.subscribeWith(observer))

    }


    fun dispose()
    {
        if(!disposables.isDisposed)
            disposables.dispose()
    }

    fun addDisposable(disposable: Disposable)
    {
        disposables.add(disposable)
    }
}