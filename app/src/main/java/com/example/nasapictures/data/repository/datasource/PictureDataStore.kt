package com.example.nasapictures.data.repository.datasource

import com.example.nasapictures.data.entity.PictureEntity
import com.example.nasapictures.domain.model.Picture
import io.reactivex.Observable

interface PictureDataStore
{
    fun getPictures(): Observable<List<PictureEntity>>
}