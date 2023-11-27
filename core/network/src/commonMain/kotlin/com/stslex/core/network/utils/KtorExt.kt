package com.stslex.core.network.utils

import io.ktor.util.date.getTimeMillis

val currentTimeMs: Long
    get() = getTimeMillis()