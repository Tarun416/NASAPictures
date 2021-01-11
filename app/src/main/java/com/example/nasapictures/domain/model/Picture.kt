package com.example.nasapictures.domain.model

import com.google.gson.annotations.SerializedName

data class Picture(
    @SerializedName("explanation")
    val explanation: String?=null,
    @SerializedName("hdurl")
    val hdurl: String?=null,
    @SerializedName("title")
    val title: String?=null,
    @SerializedName("url")
    val url: String?=null
)