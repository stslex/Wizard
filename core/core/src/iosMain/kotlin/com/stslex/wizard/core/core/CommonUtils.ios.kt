package com.stslex.wizard.core.core

import platform.Foundation.NSUUID

actual fun randomUuid(): String = NSUUID().UUIDString()