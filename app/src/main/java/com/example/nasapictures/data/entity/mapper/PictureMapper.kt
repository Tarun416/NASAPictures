package com.example.nasapictures.data.entity.mapper

import com.example.nasapictures.data.entity.PictureEntity
import com.example.nasapictures.domain.model.Picture

class PictureMapper
{
    fun transformList(pictureEntity : List<PictureEntity>) : List<Picture>
    {
        val picture = ArrayList<Picture>()
        for(pictures in pictureEntity)
        {
            picture.add(transform(pictures))
        }

        return picture
    }

    private fun transform(pictures: PictureEntity): Picture {
        val picture = Picture(hdurl = pictures.hdurl,title = pictures.title,url = pictures.url,explanation = pictures.explanation)
        return picture
    }


}