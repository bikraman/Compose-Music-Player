package com.beniezsche.composemusicplayer.models

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.core.app.NotificationCompat.StreamType

class MusicPlayer {

    private var mediaPlayer: MediaPlayer? = null

    private fun getPlayer() : MediaPlayer {

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
        getPlayer().let {
            try {
                it.setDataSource(url)
                it.prepareAsync() // might take long! (for buffering, etc)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            it.setOnPreparedListener {mediaPlayer ->
                mediaPlayer.start()
            }
        }

        getPlayer().setOnPreparedListener {
            it.start()
        }
    }


}