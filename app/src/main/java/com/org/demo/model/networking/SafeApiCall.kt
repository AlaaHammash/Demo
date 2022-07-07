package com.org.demo.model.networking

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.json.JSONException
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException

abstract class SafeApiCall {

    suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher, apiCall: suspend () -> T):
            ResultWrapper<T> {
        return withContext(dispatcher) {
            try {
                ResultWrapper.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> ResultWrapper.NetworkError
                    is HttpException -> {
                        val code = throwable.code()
                        val errorResponse = convertErrorBody(throwable)
                        ResultWrapper.GenericError(code, errorResponse)
                    }
                    is UnknownHostException -> {
                        ResultWrapper.GenericError(50, "no connection")
                    }
                    else -> {
                        ResultWrapper.GenericError(10, "Something want wrong, Please try later")
                    }
                }
            }
        }
    }

    private fun convertErrorBody(throwable: HttpException): String? {
        return try {
            throwable.response()?.errorBody()?.source()?.let {
                try {
                   return "Error Code:  ${throwable.response()?.code()} \n ${
                        throwable.response()?.message()
                    }"
                } catch (e: JSONException) {
                    null
                }

            }
        } catch (exception: Exception) {
            null
        }
    }
}
