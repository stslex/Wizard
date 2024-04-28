package com.stslex.core.ui.pager

import com.stslex.core.ui.base.AppError

sealed interface PagerLoadState {

    data object Data : PagerLoadState

    data object Initial : PagerLoadState

    data object Loading : PagerLoadState

    data object Refresh : PagerLoadState

    data object Retry : PagerLoadState

    data class Error(val error: AppError) : PagerLoadState

    data object Empty : PagerLoadState
}
