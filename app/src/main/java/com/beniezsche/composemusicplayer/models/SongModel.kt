package com.beniezsche.composemusicplayer.models

data class SongModel(
    var id: Int,
    val status: String,
    val sort : String?,
    val userCreated: String,
    val dateCreated: String,
    val userUpdated: String,
    val dateUpdated: String,
    var name: String,
    val artist: String,
    val accent: String,
    val cover: String,
    val isTopTrack: Boolean = false,
    val url: String
)