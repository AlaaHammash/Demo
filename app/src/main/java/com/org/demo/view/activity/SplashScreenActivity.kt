package com.org.demo.view.activity

import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.annotation.RequiresApi
import com.org.demo.R
import com.org.demo.databinding.ActivitySplashScreenBinding
import com.org.demo.utils.openActivity
import com.org.demo.viewModel.EquipmentViewModel
import java.util.*


class SplashScreenActivity : BaseActivity<EquipmentViewModel, ActivitySplashScreenBinding>() {


    override fun getLayoutRes(): Int {
        return R.layout.activity_splash_screen
    }

    override fun getViewModelClass(): Class<EquipmentViewModel> {
        return EquipmentViewModel::class.java
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val handler = Handler()
        val t = Timer()
        t.schedule(object : TimerTask() {
            override fun run() {
                handler.postDelayed({ startNextActivity() }, 3000)
            }
        }, 0)
    }


    private fun startNextActivity() { //handle is first time lunch = truead
        // not first time
        openActivity<MainActivity>()
        finish()
    }
}

