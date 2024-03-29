package com.dorozhan.catfacts.presentation.library

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.dorozhan.catfacts.presentation.state.*

@Composable
fun <T : Any> PagingList(
    modifier: Modifier = Modifier,
    state: LazyListState,
    items: LazyPagingItems<T>,
    itemContent: @Composable LazyListScope.(T, Int) -> Unit,
) {
    LazyColumn(modifier = modifier,
        state = state,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(items) { index, item ->
            item?.let {
                this@LazyColumn.itemContent(it, index)
            }
        }
        items.apply {
            when {
                loadState.refresh is LoadState.Loading && items.itemCount <= 0 -> {
                    item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }
                loadState.refresh is LoadState.Error -> {
                    // todo: handle error with using text
                    val e = items.loadState.refresh as LoadState.Error
                    item {
                        ErrorView(
                            modifier = Modifier.fillParentMaxSize(),
                            onClickRetry = { retry() }
                        )
                    }
                }
                loadState.append is LoadState.Error -> {
                    item {
                        ErrorItem(
                            onClickRetry = { retry() }
                        )
                    }
                }
                loadState.append is LoadState.NotLoading &&
                        loadState.append.endOfPaginationReached &&
                        items.itemCount <= 0 -> {
                    item { EmptyItemView(modifier = Modifier.fillParentMaxSize()) }
                }
            }
        }
    }
}