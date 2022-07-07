package com.org.demo.model.networking

import androidx.databinding.library.BuildConfig
import com.google.gson.GsonBuilder

import com.org.demo.model.networking.interceptors.RequestInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


var httpClient : OkHttpClient.Builder?=null
fun createOkHttpClient(requestIntercetor: RequestInterceptor): OkHttpClient {
     httpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)


    val loggingInterceptor = HttpLoggingInterceptor()
    httpClient?.addInterceptor(requestIntercetor)
   // httpClient?.addNetworkInterceptor(networkInterceptor)

     if (BuildConfig.DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClient?.addNetworkInterceptor(loggingInterceptor)

        }

    return httpClient?.build()!!
}

inline fun <reified F> createWebServiceApi(okHttpClient: OkHttpClient, url: String): F {
    val gson = GsonBuilder().serializeNulls().setLenient().create()
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    return retrofit.create(F::class.java)
}
