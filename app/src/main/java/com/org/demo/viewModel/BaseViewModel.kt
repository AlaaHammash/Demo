package com.org.demo.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

open class BaseViewModel(appContext: Context) : ViewModel(), KodeinAware {

    override val kodein: Kodein by kodein(appContext)
}