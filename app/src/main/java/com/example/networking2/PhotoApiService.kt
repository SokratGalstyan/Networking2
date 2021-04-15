package com.example.networking2

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PhotoApiService {
    @GET("photos")
    fun downLoadPhoto(): Call<PhotoModel?>

    @POST ("photos")
    fun upLoadPhoto(@Body post: PostPhotoModel.PostPhotoModelItem?): Call<PhotoModel.PhotoModelItem?>
}

object Photo{
    val retrofit = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create()).build().create(PhotoApiService::class.java)
}