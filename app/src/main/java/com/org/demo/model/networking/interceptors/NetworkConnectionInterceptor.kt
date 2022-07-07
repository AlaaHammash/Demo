package com.org.demo.model.networking.interceptors
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import com.org.demo.MyApplication
import com.org.demo.model.networking.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response


class NetworkConnectionInterceptor(/*context:Context*/) : Interceptor {

  // private val applicationContext = MyApplication.appContext

    @RequiresApi(Build.VERSION_CODES.M)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable())
            throw NoInternetException("Make sure you have an active data connection")
        return chain.proceed(chain.request())
    }

    fun isInternetAvailable(): Boolean {

      /*  val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
*/
/*        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            @Suppress("DEPRECATION")
            val netInfo = connectivityManager.activeNetworkInfo
            if (netInfo != null) {
                @Suppress("DEPRECATION")
                return (netInfo.isConnectedOrConnecting && (netInfo.type == ConnectivityManager.TYPE_WIFI || netInfo.type == ConnectivityManager.TYPE_MOBILE))
            }
        } else {
            val network = connectivityManager.activeNetwork
            if (network != null) {
                val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
                if (networkCapabilities != null) {
                    return (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || networkCapabilities.hasTransport(
                        NetworkCapabilities.TRANSPORT_WIFI
                    ))
                }
            }
        }*/
        return false

    }


}
