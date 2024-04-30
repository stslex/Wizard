package com.stslex.core.ui.pager.states

import com.stslex.core.ui.base.AppError

sealed interface PagerLoadEvents {

    data class Error(val error: AppError) : PagerLoadEvents
}