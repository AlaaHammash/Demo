package com.org.demo.model.repositories

import android.content.Context
import com.org.demo.model.models.ApiGeneralResponse
import com.org.demo.model.models.EquipmentModel
import com.org.demo.model.networking.EquipmentApi
import com.org.demo.model.networking.ResultWrapper
import com.org.demo.model.networking.SafeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.util.*

class EquipmentRepository(appContext: Context,
                          private val dispatcher: CoroutineDispatcher = Dispatchers.IO) : SafeApiCall() ,
    KodeinAware {

    private val service: EquipmentApi by instance()
    override val kodein: Kodein by kodein(appContext)


    suspend fun getEquipmentList(): ResultWrapper<ApiGeneralResponse<List<EquipmentModel>>> {
        return safeApiCall(dispatcher) { service.getEquipmentList() }
    }

}