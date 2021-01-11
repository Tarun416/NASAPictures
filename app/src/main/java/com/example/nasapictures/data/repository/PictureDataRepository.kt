package com.example.nasapictures.data.repository

import com.example.nasapictures.data.entity.PictureEntity
import com.example.nasapictures.data.entity.mapper.PictureMapper
import com.example.nasapictures.data.repository.datasource.PictureDataStoreFactory
import com.example.nasapictures.domain.PictureRepository
import com.example.nasapictures.domain.model.Picture
import io.reactivex.Observable
import io.reactivex.functions.Function

class PictureDataRepository(private val pictureDataStoreFactory: PictureDataStoreFactory,
                            private val pictureMapper: PictureMapper) : PictureRepository {
    override fun getPictures(): Observable<List<Picture>> {
       return pictureDataStoreFactory.create().getPictures().map(object : Function<List<PictureEntity>, List<Picture>> {
            override fun apply(t: List<PictureEntity>?): List<Picture> {
                return pictureMapper.transformList(t!!)
            }

        })


}}