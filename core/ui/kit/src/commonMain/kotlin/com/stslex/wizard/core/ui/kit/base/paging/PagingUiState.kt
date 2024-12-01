package com.stslex.wizard.core.ui.kit.base.paging

import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlin.math.roundToInt

@Stable
data class PagingUiState<out T : PagingItem>(
    val items: ImmutableList<T>,
    val hasMore: Boolean,
    val total: Int,
    val config: PagingConfig
) {

    val pageOffset: Int = (config.pageSize * config.pageOffset).roundToInt()

    companion object {

        fun <T : PagingItem> default(config: PagingConfig) = PagingUiState(
            items = persistentListOf<T>(),
            total = 0,
            hasMore = true,
            config = config
        )
    }
}

fun <T : PagingItem> PagingState<T>.toUi(
    pagingConfig: PagingConfig
): PagingUiState<T> = PagingUiState(
    items = result,
    hasMore = hasMore,
    total = total,
    config = pagingConfig
)