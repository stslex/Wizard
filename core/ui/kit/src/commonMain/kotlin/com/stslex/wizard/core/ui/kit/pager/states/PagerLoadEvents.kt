package com.stslex.wizard.core.ui.kit.pager.states

import com.stslex.wizard.core.ui.kit.base.AppError

sealed interface PagerLoadEvents {

    data class Error(val error: AppError) : PagerLoadEvents
}