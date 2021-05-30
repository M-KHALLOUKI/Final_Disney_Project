package com.example.disney.presentation.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DisneyApi {
    @GET("/characters")
    fun getDisneyList(@Query("page")page:Int): Call<DisneyListResponse>

    @GET("/characters/{id}")
    fun getDisneyDetail(@Path ("id")id:Int): Call<DisneyDetailResponse>
}