package com.dorozhan.catfacts.presentation.library

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.dorozhan.catfacts.R
import com.dorozhan.catfacts.presentation.flow.destinations.FavoritesScreenDestination

@Composable
private fun BaseTitleContentProvider(
    content: @Composable () -> Unit,
) = ProvideTextStyle(value = MaterialTheme.typography.h6) {
    CompositionLocalProvider(
        LocalContentAlpha provides ContentAlpha.high,
        content = content
    )
}

@Composable
fun BackIcon(
    onBackClick: () -> Unit,
) {
    IconButton(onClick = { onBackClick() }) {
        Icon(
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = "Back",
        )
    }
}

@Composable
fun BackAppBar(
    text: String = "",
    title: @Composable () -> Unit = { Text(text = text) },
    onBackClick: () -> Unit,
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        navigationIcon = {
            BackIcon(onBackClick = onBackClick)
        },
        title = title,
        actions = actions
    )
}

@Composable
fun CatalogAppBar(
    title: String,
    onSearchClick: () -> Unit = {},
    onFavoritesClick: () -> Unit = {}
) {
    TopAppBar(
        title = { Text(text = title) },
        actions = {
            IconButton(
                onClick = onSearchClick
            ) {
                Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search")
            }
            IconButton(
                onClick = onFavoritesClick
            ) {
                Icon(imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Favorites")
            }
        }
    )
}

@Composable
fun SearchAppBar(
    text: String,
    textPlaceholder: String = stringResource(id = R.string.search),
    onBackClick: () -> Unit = {},
    onTextChange: (String) -> Unit = {},
    onSearchClick: (String) -> Unit = {},
) {
    val onBackClickListener: () -> Unit = {
        onTextChange("")
        onBackClick()
    }

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    BackHandler(onBack = onBackClickListener)

    BackAppBar(
        title = {
            BaseTitleContentProvider {
                NoPaddingTextField(
                    value = text,
                    onValueChange = onTextChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                    placeholder = {
                        Text(
                            text = textPlaceholder,
                            color = MaterialTheme.colors.surface.copy(alpha = ContentAlpha.medium),
                            style = MaterialTheme.typography.h6
                        )
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            onSearchClick(text)
                        }
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        cursorColor = MaterialTheme.colors.surface.copy(alpha = ContentAlpha.medium)
                    )
                )
            }

        },
        onBackClick = onBackClickListener,
        actions = {
            if (text.isNotEmpty()) {
                IconButton(
                    onClick = { onTextChange("") }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close"
                    )
                }
            }
        }
    )
}

@Composable
@Preview
fun BackAppBarPreview() {
    BackAppBar(
        text = "Top Bar title",
        onBackClick = {}
    )
}

@Composable
@Preview
fun SearchAppBarPreview() {
    SearchAppBar(
        text = "Top Bar title",
        onBackClick = {},
        onTextChange = {},
        onSearchClick = {}
    )
}