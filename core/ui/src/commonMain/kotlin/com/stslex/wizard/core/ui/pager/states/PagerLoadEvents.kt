package com.stslex.wizard.core.ui.pager.states

import com.stslex.wizard.core.ui.base.AppError

sealed interface PagerLoadEvents {

    data class Error(val error: AppError) : PagerLoadEvents
}