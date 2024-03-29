package com.beniezsche.composemusicplayer

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beniezsche.composemusicplayer.models.MusicPlayer
import com.beniezsche.composemusicplayer.models.SongModel
import com.beniezsche.composemusicplayer.ui.theme.ComposeMusicPlayerTheme
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

class MusicPlayerActivity : ComponentActivity() {

    lateinit var song: SongModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        song = intent.getParcelableExtra("song")!!
        setContent {
            ComposeMusicPlayerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MusicPlayerUI(song = song)
                }
            }
        }
    }

    @Composable
    fun PlaybackControls() {

        val player = remember { mutableStateOf(MusicPlayer()) }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(
                onClick = { /* Handle skip to previous */ }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Previous"
                )
            }
            IconButton(
                onClick = { player.value.playAudioFromUrl(song.url!!) }
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Play/Pause"
                )
            }
            IconButton(
                onClick = { /* Handle skip to next */ }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Next"
                )
            }
        }
    }

    @Composable
    fun MusicPlayerUI(song: SongModel) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            AlbumArt(song.cover!!)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = song.name!!, style = TextStyle(fontSize = 20.sp), fontWeight = FontWeight.Bold)
            Text(text = song.artist!!, style = TextStyle(fontSize = 16.sp))
            Spacer(modifier = Modifier.height(70.dp))
            LinearProgressIndicator(progress = 0.5f, modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp, end = 40.dp))
            Spacer(modifier = Modifier.height(70.dp))


            PlaybackControls()
        }
    }

    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    fun AlbumArt(albumArt: String) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            GlideImage(
                model = "https://cms.samespace.com/assets/$albumArt",
                contentDescription = "Album Art",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(350.dp)
            )
        }
    }




    @Composable
    fun Greeting2(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview2() {
        ComposeMusicPlayerTheme {
            MusicPlayerUI(song)
        }
    }
}



