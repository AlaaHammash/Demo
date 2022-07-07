package com.org.demo.utils


import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.widget.*
import com.org.demo.R
import com.wang.avi.AVLoadingIndicatorView
import java.util.*



object UiUtils {
    var avLoadingIndicatorView: AVLoadingIndicatorView? = null
    var loading_view: AlertDialog? = null


    fun showProccesDialog(context: Context?, activity: Activity?) {
        try {
            val dialogBuilder = AlertDialog.Builder(context)
            val inflater = activity!!.layoutInflater
            val dialogView = inflater.inflate(R.layout.loading_progressbar, null)
            avLoadingIndicatorView = dialogView!!.findViewById(R.id.avi) as AVLoadingIndicatorView
            dialogBuilder.setView(dialogView)
            dialogBuilder.setCancelable(false)
            loading_view = dialogBuilder.create()
            loading_view!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)
          // loading_view!!.setCancelable(true)
            loading_view!!.setCancelable(false)
            loading_view!!.show()
            avLoadingIndicatorView!!.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun dismissProccessDialog() {
        try {
            loading_view?.dismiss()
            loading_view = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun isProgressShowing(): Boolean {
        try {
            return loading_view != null
        } catch (e: Exception) {

        }

        return true

    }

}

