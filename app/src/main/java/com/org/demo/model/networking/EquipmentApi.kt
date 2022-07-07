package com.org.demo.model.networking

import com.org.demo.model.models.ApiGeneralResponse
import com.org.demo.model.models.EquipmentModel
import retrofit2.http.GET


interface EquipmentApi {

    @GET("dynamodb-writer")
    suspend fun getEquipmentList(): ApiGeneralResponse<List<EquipmentModel>>
}
