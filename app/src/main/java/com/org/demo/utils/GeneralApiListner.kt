package com.org.demo.utils


interface GeneralApiListner {
    fun onSuccess()
    fun onFailuer(message: String)
    fun onLoading()
    fun onNetworkError()

}