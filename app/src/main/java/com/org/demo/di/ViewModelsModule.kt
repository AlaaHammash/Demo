package com.org.demo.di

import com.org.demo.viewModel.BaseViewModel
import com.org.demo.viewModel.EquipmentViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

val viewModelsModule = Kodein.Module("view_models_module") {
    bind<BaseViewModel>() with provider { BaseViewModel(instance()) }
    bind<EquipmentViewModel>() with provider { EquipmentViewModel(instance()) }
}