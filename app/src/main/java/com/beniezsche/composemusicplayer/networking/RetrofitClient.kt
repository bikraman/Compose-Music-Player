package com.beniezsche.composemusicplayer.networking

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://cms.samespace.com"
    private var INSTANCE : APIs? = null

    fun getClient() : APIs {

        if (INSTANCE == null) {
            INSTANCE = Retrofit.Builder()

                .baseUrl(BASE_URL)

                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIs::class.java)


            return INSTANCE!!
        }
        else
            return INSTANCE!!

    }
}