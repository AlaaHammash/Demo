package com.org.demo.di

import com.org.demo.model.repositories.EquipmentRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val repositoryModule = Kodein.Module("repository_module") {
    bind<EquipmentRepository>() with singleton { EquipmentRepository(instance()) }
}