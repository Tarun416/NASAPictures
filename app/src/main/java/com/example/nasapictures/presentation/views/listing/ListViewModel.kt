package com.example.nasapictures.presentation.views.listing

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasapictures.domain.interactor.DefaultObserver
import com.example.nasapictures.domain.interactor.GetPictures
import com.example.nasapictures.domain.model.Picture
import com.example.nasapictures.presentation.utils.Resource
import com.example.nasapictures.presentation.utils.ResourceState

class ListViewModel (private val getPictures: GetPictures): ViewModel()
{
   val pictureLiveData : MutableLiveData<Resource<List<Picture>>> = MutableLiveData()

   fun getPictures()
   {
       pictureLiveData.postValue(Resource(ResourceState.LOADING,null,null))
       getPictures.execute(PictureObserver(),null)
   }

   private inner class PictureObserver : DefaultObserver<List<Picture>>()
   {
       override fun onComplete() {

       }

      override fun onError(e: Throwable?) {
          pictureLiveData.postValue(Resource(ResourceState.ERROR,null,null))
       }

       override fun onNext(pictures: List<Picture>) {
           pictureLiveData.postValue(Resource(ResourceState.SUCCESS,pictures,null))
       }


   }

}