package com.dorozhan.catfacts.presentation.state

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dorozhan.catfacts.R

@Composable
fun LoadingView(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
@Preview
fun LoadingViewPreview() {
    LoadingView()
}

@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    onClickRetry: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RetryImage(
            modifier = Modifier.size(40.dp),
            onClick = onClickRetry
        )
    }
}

@Composable
@Preview
fun ErrorViewPreview() {
    ErrorView {}
}

@Composable
fun LoadingItem() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
            .size(24.dp),
        strokeWidth = 2.dp
    )
}


@Composable
@Preview
fun LoadingItemPreview() {
    LoadingItem()
}

@Composable
fun ErrorItem(
    onClickRetry: () -> Unit
) {
    RetryImage(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentWidth(Alignment.CenterHorizontally),
        onClick = onClickRetry
    )
}

@Composable
fun RetryImage(
    modifier: Modifier,
    onClick: () -> Unit
) {
    IconButton(onClick = { onClick.invoke() }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_refresh_24),
            contentDescription = stringResource(id = R.string.reload)
        )
    }
}

@Composable
@Preview
fun ErrorItemPreview() {
    ErrorItem {}
}