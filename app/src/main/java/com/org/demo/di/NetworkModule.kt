package com.org.demo.di



import com.org.demo.model.networking.interceptors.NetworkConnectionInterceptor
import com.org.demo.model.networking.interceptors.RequestInterceptor
import com.org.demo.BuildConfig

import com.org.demo.model.networking.EquipmentApi
import com.org.demo.model.networking.createOkHttpClient
import com.org.demo.model.networking.createWebServiceApi
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import org.kodein.di.generic.with

private const val TAG_SERVER_URL = "server_url_const"

val networkModule = Kodein.Module("network_module") {
    constant(TAG_SERVER_URL) with BuildConfig.server_type
    bind<RequestInterceptor>() with singleton { RequestInterceptor() }
    bind<OkHttpClient>() with singleton { createOkHttpClient(instance()) }
    bind<EquipmentApi>() with singleton { createWebServiceApi(instance(), instance(TAG_SERVER_URL)) }
}