package com.org.demo.model.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ApiGeneralResponse<T : Any?>(
    @SerializedName("statusCode") var response_code: Int?,
    @SerializedName("message") var message: String?,
    @SerializedName("results") var results: T?
)

