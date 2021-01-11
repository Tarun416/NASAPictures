package com.example.nasapictures.data.local

import com.example.nasapictures.data.entity.PictureEntity
import com.example.nasapictures.domain.model.Picture
import io.reactivex.Observable
import java.util.*

interface PictureLocal
{
    fun getPictures() : Observable<List<PictureEntity>>
}