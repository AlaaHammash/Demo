package com.org.demo.model.networking.interceptors

import com.org.demo.model.networking.ApiException
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class RequestInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val original = chain.request()
        val shouldSkipAuth = original.header("ShouldShouldSkipAuth").equals("true")
        val builder = original.newBuilder()

/*

        if (prefObject.getPrefs(PrefSingleton.TKN_K).isNotEmpty() && !shouldSkipAuth && !apikey)
            builder.addHeader(
                "Authorization", "Bearer " + prefObject.getPrefs(
                    PrefSingleton.TKN_K
                )
            )
*/



        val request = builder
            .addHeader("Accept-Language", "en")
            .addHeader("Content-Type", "application/json; charset=UTF-8")
            .method(original.method(), original.body())
            .build()

        try {
            return chain.proceed(request)

        } catch (e: Throwable) {

            if (e is IOException) {

                throw ApiException("If IOException=>%s")
            } else {

                throw ApiException("Else IOException=>%s")
            }
        }

    }
}