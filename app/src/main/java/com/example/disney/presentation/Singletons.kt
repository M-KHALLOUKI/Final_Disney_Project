package com.example.disney.presentation

import com.example.disney.presentation.api.DisneyApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Singletons {
    companion object{
        val disneyApi: DisneyApi = Retrofit.Builder()
            .baseUrl("https://api.disneyapi.dev")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DisneyApi::class.java)
    }
}