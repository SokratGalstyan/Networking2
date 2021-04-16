package com.example.networking2

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface NewsApi {
    @GET("v2/everything")
    fun getNews(@QueryMap options: Map<String, String>, @QueryMap pageOptions: Map<String, Int>): Call<NewsModel>
}

object NewsResponse{
    val retrofit = Retrofit.Builder().baseUrl("https://newsapi.org").addConverterFactory(GsonConverterFactory.create())
            .build().create(NewsApi::class.java)
}