package com.example.nasapictures.data.repository.datasource

import com.example.nasapictures.data.local.PictureLocal

class PictureDataStoreFactory (private val pictureLocal: PictureLocal)
{
    fun create() : PictureDataStore
    {
        return PictureLocalStore(pictureLocal)
    }

}