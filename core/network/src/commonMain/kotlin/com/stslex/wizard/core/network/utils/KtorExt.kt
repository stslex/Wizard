package com.stslex.wizard.core.network.utils

import io.ktor.util.date.getTimeMillis

val currentTimeMs: Long
    get() = getTimeMillis()