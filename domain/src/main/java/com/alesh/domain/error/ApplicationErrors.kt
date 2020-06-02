package com.alesh.domain.error

sealed class ApplicationErrors {

    object Generic : ApplicationErrors()
    object TimeOut : ApplicationErrors()
    object Server : ApplicationErrors()
    object NotFound : ApplicationErrors()
    object NoInternetConnection : ApplicationErrors()
}