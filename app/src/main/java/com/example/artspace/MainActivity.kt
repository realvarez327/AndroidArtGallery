package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceLayout(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ArtSpaceLayout( modifier: Modifier = Modifier) {
    val artworkList = mutableListOf<Artwork>()
    //populate artwork list
    artworkList.add(Artwork(
        artist = "John William Waterhouse",
        year = 1888,
        imageResourceId = R.drawable.cleopatra___john_william_waterhouse,
        medium = "oil on canvas",
        title = "Cleopatra",
        authorPicId = R.drawable.john_william_waterhouse

    ))

    artworkList.add(Artwork(
        artist = "John William Waterhouse",
        medium = "oil on canvas",
        year = 1908,
        imageResourceId = R.drawable._60px_gather_ye_rosebuds_while_ye_may,
        title = "Gather Ye Rosebuds While Ye May",
        authorPicId = R.drawable.john_william_waterhouse
    ))

    artworkList.add(Artwork(
        artist = "John William Waterhouse",
        title = "Annunciation",
        year = 1914,
        medium = "oil on canvas",
        imageResourceId = R.drawable._60px_john_william_waterhouse___the_annunciation,
        authorPicId = R.drawable.john_william_waterhouse
    ))

    artworkList.add(Artwork(
        artist = "Jennie Augusta Brownscombe",
        title = "Maidens and Roses",
        year = 1906,
        medium = "Unknown",
        imageResourceId = R.drawable.jennie_brownscombe_maidens_and_roses_1906_approximate_original_size_24x36,
        authorPicId = R.drawable.jennie_brownscombe
    ))

    artworkList.add(Artwork(
        artist = "Beatrice Offor",
        title = "Rosary",
        year = 1910,
        medium = "oil on canvas",
        imageResourceId = R.drawable.offor_rosary,
        authorPicId = R.drawable.beatrice_offor_in_her_studio
    ))

    val width = (LocalConfiguration.current.screenWidthDp * 0.75)

    var currentArtIndex by remember { mutableStateOf(0) }
    Box(
        modifier = modifier.fillMaxSize()
    ){
        Column (
            modifier = modifier.statusBarsPadding().width(width.dp).background(color = colorResource(R.color.pink_80)).align(
                Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = painterResource(artworkList[currentArtIndex].imageResourceId),
                contentDescription = "art img",
                modifier = modifier.width((width*0.9).dp).align(Alignment.CenterHorizontally).padding(top = 16.dp),
                contentScale = ContentScale.FillWidth

            )
            Row (
                horizontalArrangement = Arrangement.Center,
                modifier = modifier.padding(bottom = 4.dp, top = 4.dp).width((width*0.9).dp).align(Alignment.CenterHorizontally)

            ){
                Image(
                    painter = painterResource(artworkList[currentArtIndex].authorPicId),
                    contentDescription = "author image",
                    modifier = modifier.width(150.dp),
                    contentScale = ContentScale.FillWidth


                )
                Column(modifier = modifier.offset(x = 8.dp)) {
                    Text(
                        text = "${artworkList[currentArtIndex].title} \nBy: ${artworkList[currentArtIndex].artist}"
                    )
                    Text(
                        text = artworkList[currentArtIndex].year.toString(),
                        fontStyle = FontStyle.Italic
                    )
                    Text(
                        text = "Medium: ${artworkList[currentArtIndex].medium}",
                        modifier = modifier.padding(end = 8.dp)
                    )

                }
            }

            Row (
                modifier = modifier.padding(bottom = 4.dp)
            ){

                Button(
                    onClick = {
                        currentArtIndex--
                        if (currentArtIndex<0){
                            currentArtIndex = artworkList.size-1
                        }
                    }
                ) {
                    Text(
                        text = "Previous!"
                    )
                }

                Button(
                    onClick = {
                        currentArtIndex++
                        if (currentArtIndex>= artworkList.size){
                            currentArtIndex = 0
                        }
                    }
                ) {
                    Text(
                        text = "Next!"
                    )

                }
            }
        }
    }
}

class Artwork(var artist:String, var year:Int, var imageResourceId:Int, var medium:String, var title:String, var authorPicId:Int){

}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}