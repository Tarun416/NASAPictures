package com.example.nasapictures.domain.interactor

import com.example.nasapictures.domain.PictureRepository
import com.example.nasapictures.domain.model.Picture
import io.reactivex.Observable

class GetPictures(private val pictureRepository: PictureRepository) : SingleUseCase<List<Picture>,Void>()
{
    override fun buildUseCaseObservable(params: Void?): Observable<List<Picture>> {
        return pictureRepository.getPictures()
    }

}