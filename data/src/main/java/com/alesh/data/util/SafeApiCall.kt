package com.alesh.data.util

import com.alesh.domain.error.ApplicationErrors
import com.alesh.domain.model.result.Result
import retrofit2.HttpException
import java.net.ConnectException
import java.net.HttpURLConnection.HTTP_INTERNAL_ERROR
import java.net.HttpURLConnection.HTTP_NOT_FOUND
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <T> safeApiCall(apiCall: suspend () -> T): Result<T> {
    return try {
        Result.Success(apiCall.invoke())
    } catch (throwable: Throwable) {
        when (throwable) {
            is SocketTimeoutException   -> Result.Error(ApplicationErrors.TimeOut)
            is ConnectException         -> Result.Error(ApplicationErrors.NoInternetConnection)
            is UnknownHostException     -> Result.Error(ApplicationErrors.NoInternetConnection)
            is IllegalArgumentException -> Result.Error(ApplicationErrors.Server)
            is HttpException            -> {
                when (throwable.code()) {
                    HTTP_NOT_FOUND      -> Result.Error(ApplicationErrors.NotFound)
                    HTTP_INTERNAL_ERROR -> Result.Error(ApplicationErrors.Server)
                    else                -> Result.Error(ApplicationErrors.Generic)
                }
            }
            else                        -> {
                Result.Error(ApplicationErrors.Generic)
            }
        }
    }
}
