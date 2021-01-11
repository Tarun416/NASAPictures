package com.example.nasapictures.domain

import com.example.nasapictures.domain.model.Picture
import io.reactivex.Observable
import java.util.*

interface PictureRepository
{
     fun getPictures() : Observable<List<Picture>>
}