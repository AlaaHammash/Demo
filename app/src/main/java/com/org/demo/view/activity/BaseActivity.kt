package com.org.demo.view.activity


import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.content.res.Resources
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.ConfigurationCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.org.demo.R
import com.org.demo.utils.*
import com.org.demo.viewModel.factories.GeneralViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.util.*

import kotlin.Exception


abstract class BaseActivity<V :ViewModel, D : ViewDataBinding> : AppCompatActivity(), KodeinAware  {
    override val kodein: Kodein by kodein(this)

    lateinit var binding: D

    private val viewModelFactory: GeneralViewModelFactory by instance()
    protected lateinit var viewModel: V
    protected abstract fun getViewModelClass(): Class<V>

    private val networkMonitor = NetworkMonitorUtil(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     //   val preferredLanguage = prefObject.getPrefs(PrefSingleton.CURRENT_LANGUAGE)
        val locale = Locale("en")
        Locale.setDefault(locale)
        val res: Resources = resources
        val config = Configuration(res.configuration)
        config.locale = locale
        config.setLayoutDirection(locale)
        res.updateConfiguration(config, res.displayMetrics)

        binding = DataBindingUtil.setContentView<D>(this, getLayoutRes())

        viewModel = viewModelFactory.create(getViewModelClass())

        supportActionBar?.hide()


        networkMonitor.result = { isAvailable, type ->
            runOnUiThread {
                when (isAvailable) {
                    true -> {
                    }
                    false -> {
                        // Go to No Internet Activity
                        toast(R.string.networking_error)
                    }
                }
            }
        }


    }

    @LayoutRes
    protected abstract fun getLayoutRes(): Int


    override fun onResume() {
        super.onResume()
        try {
            networkMonitor.register()
        } catch (e: Exception) {
        }
    }

    override fun onPause() {
        super.onPause()
        try {
            networkMonitor.unregister()
        } catch (e: Exception) {

        }
    }



    override fun onBackPressed() {
            super.onBackPressed()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }


}



