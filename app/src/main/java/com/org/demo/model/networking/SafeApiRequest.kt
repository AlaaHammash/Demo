package com.jorange.xyz.model.networking

import com.google.gson.JsonSyntaxException
import com.org.demo.model.networking.ApiException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.lang.Exception

abstract class SafeApiRequest {

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        try {
            val response = call.invoke()
            if (response.isSuccessful) {
                return response.body()!!
            } else {
                when {
                    response.code() == 404 -> {
                        throw ApiException("Something went wrong")
                    }
                    response.code() == 500 -> {
                        throw ApiException("Something went wrong, Please try later")
                    }
                    response.code() == 400 -> {
                        throw ApiException("Something went wrong, Please try later")
                    }
                    response.code() == 403 -> {
                        throw ApiException("please login again")
                    }
                    else -> {
                        val error = response.errorBody()?.toString()
                        val message = StringBuilder()
                        error?.let {
                            try {
                                message.append("Error Code:  ${response.code()}")
//                                message.append(JSONObject(it).getString("message"))
//                                message.append("\n")
                                message.append(JSONObject(it).getString("error"))
                            } catch (e: JSONException) {
                            }
                            message.append("\n")
                        }
                        // message.append("Error Code: ${response.code()}")
                        try {
                            throw ApiException(message.toString())
                        } catch (e: Exception) {
                            throw ApiException(e.message.toString())
                        }
                    }
                }

            }


        }  catch (ex: JsonSyntaxException) {
            throw ApiException(ex.message!!)
        }
        catch (ex: Exception) {
            throw ApiException(ex.message.toString())
        }
    }
}

/*

class ApiEmptyResponse<T> : ApiResponse<T>()
data class ApiErrorResponse<T>(val errorMessage: String) : ApiResponse<T>()


@Suppress("unused") // T is used in extending classes
sealed class ApiResponse<T> {
    companion object {
        fun <T> create(error: Throwable): ApiErrorResponse<T> {
            return ApiErrorResponse(error.message ?: "unknown error")
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(body = body )
                }
            } else {
                val msg = response.errorBody()?.string()
                val errorMsg = if (msg.isNullOrEmpty()) {
                    response.message()
                } else {
                    msg
                }
                ApiErrorResponse(errorMsg ?: "unknown error")
            }
        }
    }
}

data class ApiSuccessResponse<T>(
    val body: T
) : ApiResponse<T>()
*/
