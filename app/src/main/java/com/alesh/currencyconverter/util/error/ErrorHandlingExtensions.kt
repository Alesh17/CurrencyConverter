package com.alesh.currencyconverter.util.error

import androidx.annotation.StringRes
import com.alesh.currencyconverter.R
import com.alesh.domain.error.ApplicationErrors

@StringRes
fun ApplicationErrors.message() =

    when (this) {

        ApplicationErrors.Generic              -> R.string.generic_error
        ApplicationErrors.Server               -> R.string.server_error
        ApplicationErrors.NoInternetConnection -> R.string.noInternetConnection_error
        ApplicationErrors.TimeOut              -> R.string.timeout_error
        ApplicationErrors.NotFound             -> R.string.notFound_error
    }
