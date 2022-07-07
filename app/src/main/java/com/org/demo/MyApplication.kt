package com.org.demo

import android.app.Application
import android.content.Context
import androidx.lifecycle.LifecycleObserver
import com.org.demo.databinding.ActivityMainBinding.bind
import com.org.demo.di.networkModule
import com.org.demo.di.repositoryModule
import com.org.demo.di.viewModelsModule
import com.org.demo.viewModel.factories.GeneralViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton


class MyApplication : Application(), KodeinAware, LifecycleObserver {


    override val kodein: Kodein by Kodein.lazy {
        bind<GeneralViewModelFactory>() with singleton { GeneralViewModelFactory(applicationContext) }

        import(androidXModule(this@MyApplication))
        import(networkModule)
        import(repositoryModule)
        import(viewModelsModule)

    }

    override fun onCreate() {
        super.onCreate()


    }

}
