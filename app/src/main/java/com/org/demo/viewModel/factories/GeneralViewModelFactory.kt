package com.org.demo.viewModel.factories

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.org.demo.MyApplication
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.TT
import org.kodein.di.direct

class GeneralViewModelFactory(appContext: Context) : ViewModelProvider.Factory, KodeinAware {
    override val kodein: Kodein =
        (appContext as MyApplication).kodein // we can use `by kodein(appContext)` also

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        kodein.direct.Instance(TT(modelClass))
}