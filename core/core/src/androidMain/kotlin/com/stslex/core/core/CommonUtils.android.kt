package com.stslex.core.core

import java.util.UUID

actual fun randomUuid(): String = UUID.randomUUID().toString()
