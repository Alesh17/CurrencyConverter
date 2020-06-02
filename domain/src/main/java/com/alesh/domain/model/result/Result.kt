package com.alesh.domain.model.result

import com.alesh.domain.error.ApplicationErrors

sealed class Result<out T> {
    data class Success<out T>(val value: T) : Result<T>()
    data class Error(val error: ApplicationErrors) : Result<Nothing>()
}