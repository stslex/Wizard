package com.stslex.core.core

import platform.Foundation.NSUUID

actual fun randomUuid(): String = NSUUID().UUIDString()