package com.beniezsche.composemusicplayer.models

import com.google.gson.annotations.SerializedName

class SongsResponse {

    @SerializedName("data")
    var data: List<SongModel> ? = null
}