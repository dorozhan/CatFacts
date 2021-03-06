package com.dorozhan.catfacts.presentation.flow.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.dorozhan.catfacts.R
import com.dorozhan.catfacts.presentation.library.BackAppBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(navArgsDelegate = DetailsNavArgs::class)
@Composable
fun CatDetailsScreen(
    detailsViewModel: DetailsViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
) {
    val breedState = detailsViewModel.breedLiveData.observeAsState()
    Scaffold(
        topBar = {
            BackAppBar(
                text = breedState.value?.title ?: "",
                onBackClick = { navigator.navigateUp() })
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = breedState.value?.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.ic_cat_placeholder_512),
                    error = painterResource(id = R.drawable.ic_cat_placeholder_512),
                )
            }
        }
    )
}