package com.beniezsche.composemusicplayer.models

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer

class MusicPlayer {

    private var mediaPlayer: MediaPlayer? = null

    fun getPlayer() : MediaPlayer {

        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer().apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
//            setDataSource(url)
//            prepare() // might take long! (for buffering, etc)
//            start()
            }

            return mediaPlayer!!
        }

        else {
            return mediaPlayer!!
        }


    }

    fun playAudioFromUrl(url: String) {
        mediaPlayer?.setDataSource(url)
        mediaPlayer?.prepare() // might take long! (for buffering, etc)
        mediaPlayer?.start()
    }
}