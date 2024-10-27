package com.stslex.wizard.core.core

import java.util.UUID

actual fun randomUuid(): String = UUID.randomUUID().toString()
