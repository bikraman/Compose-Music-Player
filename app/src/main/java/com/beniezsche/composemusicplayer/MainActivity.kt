package com.beniezsche.composemusicplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beniezsche.composemusicplayer.models.SongModel
import com.beniezsche.composemusicplayer.models.SongsResponse
import com.beniezsche.composemusicplayer.networking.RetrofitClient
import com.beniezsche.composemusicplayer.ui.theme.ComposeMusicPlayerTheme
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val itemList = remember { mutableStateListOf<SongModel>() }

            LaunchedEffect(key1 = "hello", block = {
                RetrofitClient.getClient().getSongs().enqueue(object: Callback<SongsResponse> {
                    override fun onResponse(call: Call<SongsResponse>, response: Response<SongsResponse>) {
                        if (response.isSuccessful) {
                            response.body()?.data?.let {
                                itemList.addAll(it)
                            }
                        }
                    }

                    override fun onFailure(call: Call<SongsResponse>, t: Throwable) {

                    }

                })
            } )
            ComposeMusicPlayerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LazyColumn (modifier = Modifier.fillMaxSize()) {
                        items(
                            count = itemList.size,
                            key = {
                                itemList[it].id
                            },
                            itemContent = {index: Int ->
                                SongItem(albumCoverIcon = itemList[index].cover , name = itemList[index].name, artistName = itemList[index].artist)
                            }
                        )
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SongItem(albumCoverIcon: String, name: String, artistName: String) {

    Row(modifier = Modifier.padding(20.dp)){
        GlideImage(model = "https://cms.samespace.com/assets/$albumCoverIcon",
            contentDescription = name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(48.dp)
        )
        Column(modifier = Modifier.padding(start = 10.dp)) {
            Text(text = name, fontSize = 17.sp)
            Text(text = artistName, fontSize = 15.sp)
        }
    }

}
