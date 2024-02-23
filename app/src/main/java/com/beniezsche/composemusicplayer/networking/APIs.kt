package com.beniezsche.composemusicplayer.networking

import com.beniezsche.composemusicplayer.models.SongModel
import com.beniezsche.composemusicplayer.models.SongsResponse
import retrofit2.Call
import retrofit2.http.GET

interface APIs {

    @GET("/items/songs")
    fun getSongs() : Call<SongsResponse>


}