package com.example.nasapictures.data.local

import com.example.nasapictures.data.entity.PictureEntity
import com.example.nasapictures.data.Utils
import io.reactivex.Observable
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException

class PictureLocalImpl : PictureLocal {

    override fun getPictures(): Observable<List<PictureEntity>> {
        val pictureEntity = getPictureListFromJson()
        return Observable.just(pictureEntity)
    }

    fun getPictureListFromJson() : List<PictureEntity>
    {

        val pictureslist = ArrayList<PictureEntity>()
        try {
            val jsonDataString: String = Utils.readJSONDataFromFile()
            val jsonArray = JSONArray(jsonDataString)
            for (i in 0 until jsonArray.length()) {
                val itemObj = jsonArray.getJSONObject(i)
                val url = itemObj.getString("url")
                val title = itemObj.getString("title")
                val explanation = itemObj.getString("explanation")
                val hdurl = itemObj.getString("hdurl")

                val picture= PictureEntity(url = url,title = title,explanation = explanation,hdurl = hdurl)

                pictureslist.add(picture)
            }

        } catch (e: JSONException) {
           // Log.d(TAG, "addItemsFromJSON: ", e)
        } catch (e: IOException) {
           // Log.d(TAG, "addItemsFromJSON: ", e)
        }

        return pictureslist
    }
}