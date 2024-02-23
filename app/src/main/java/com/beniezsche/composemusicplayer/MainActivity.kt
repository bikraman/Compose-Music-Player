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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beniezsche.composemusicplayer.models.SongModel
import com.beniezsche.composemusicplayer.models.SongsResponse
import com.beniezsche.composemusicplayer.networking.RetrofitClient
import com.beniezsche.composemusicplayer.ui.theme.ComposeMusicPlayerTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val songList = ArrayList<SongModel>()

        RetrofitClient.getClient().getSongs().enqueue(object: Callback<SongsResponse> {
            override fun onResponse(call: Call<SongsResponse>, response: Response<SongsResponse>) {
                if (response.isSuccessful) {
                    response.body()?.data?.let {
                        songList.addAll(it)
                    }
                }
            }

            override fun onFailure(call: Call<SongsResponse>, t: Throwable) {

            }

        })

//        for(i in 1..100) {
//            val songModel = SongModel()
//            songModel.id = i
//            songModel.name = "Song $i"
//            songModel.artist = "Artist $i"
//            songList.add(songModel)
//        }

        setContent {

            val itemList = remember { mutableListOf(null) }
            ComposeMusicPlayerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LazyColumn {
//                        items()
                        items(
                            count = songList.size,
                            key = {
                                songList[it].id
                            },
                            itemContent = {index: Int ->
                                SongItem(albumCoverIcon = "nothing" , name = songList[index].name!!, artistName = songList[index].artist!! )
                            }
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun SongItem(albumCoverIcon: String, name: String, artistName: String) {

    Row(modifier = Modifier.padding(20.dp)){
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = name.toString(),
            modifier = Modifier.size(48.dp))
        Column(modifier = Modifier.padding(start = 10.dp)) {
            Text(text = name, fontSize = 17.sp)
            Text(text = artistName, fontSize = 15.sp)
        }
    }

}
