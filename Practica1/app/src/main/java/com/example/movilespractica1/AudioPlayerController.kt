package com.example.movilespractica1

import android.content.Context
import android.media.MediaPlayer
import androidx.annotation.RawRes

class AudioPlayerController(context: Context, @RawRes audioResId: Int) {
    private val mediaPlayer: MediaPlayer = MediaPlayer.create(context, audioResId)

    val isPlaying: Boolean
        get() = mediaPlayer.isPlaying

    fun play() {
        if (!mediaPlayer.isPlaying) {
            mediaPlayer.start()
        }
    }

    fun pause() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        }
    }

    fun release() {
        mediaPlayer.release()
    }

}
