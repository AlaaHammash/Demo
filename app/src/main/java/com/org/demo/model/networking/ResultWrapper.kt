package com.org.demo.model.networking

/*sealed class Result<out T:Any> {
    data class Success<out T:Any>(val data:T): Result<T>()
    data class Error(val exception:Exception): Result<Nothing>()
}*/

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class GenericError(val code: Int? = null, val error: String? = null): ResultWrapper<Nothing>()
    object NetworkError: ResultWrapper<Nothing>()
}