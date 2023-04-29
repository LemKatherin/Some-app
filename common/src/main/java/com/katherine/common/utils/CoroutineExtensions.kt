package com.katherine.common.utils

import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

suspend fun <T> withContextCatching(context: CoroutineContext, block: suspend () -> T) =
    withContext(context) {
        runCatching {
            block.invoke()
        }
    }