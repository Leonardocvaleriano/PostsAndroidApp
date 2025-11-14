package com.codeplace.postsandroidapp.core.data.network

import com.codeplace.postsandroidapp.core.domain.DataError
import com.codeplace.postsandroidapp.core.domain.Result
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import okio.IOException


inline suspend fun <reified T> safeApiCall(
    httpResponseCall: suspend () -> HttpResponse
): Result<T, DataError.Network> {
    return try {
        val response = httpResponseCall()
        when (response.status.value) {
            in 200..299 -> {
                val body: T = response.body()
                Result.Success(body)
            }
            401 -> Result.Error(DataError.Network.UNAUTHORIZED)
            408 -> Result.Error(DataError.Network.REQUEST_TIMEOUT)
            429 -> Result.Error(DataError.Network.TOO_MANY_REQUESTS)
            in 500..599 -> Result.Error(DataError.Network.SERVER_ERROR)
            else -> Result.Error(DataError.Network.UNKNOWN)
        }
    } catch (e: IOException) {
        Result.Error(DataError.Network.NO_INTERNET)
    } catch (e: Exception) {
        Result.Error(DataError.Network.UNKNOWN)
    }
}