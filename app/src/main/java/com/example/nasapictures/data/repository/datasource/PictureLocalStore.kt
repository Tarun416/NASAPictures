package com.example.nasapictures.data.repository.datasource

import com.example.nasapictures.data.entity.PictureEntity
import com.example.nasapictures.data.local.PictureLocal
import io.reactivex.Observable

class PictureLocalStore(private val  pictureLocal : PictureLocal) : PictureDataStore {

    override fun getPictures(): Observable<List<PictureEntity>> {
        return pictureLocal.getPictures()
    }
}