package com.org.demo.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.org.demo.model.models.EquipmentModel
import com.org.demo.model.networking.ApiException
import com.org.demo.model.networking.NoInternetException
import com.org.demo.model.networking.ResultWrapper
import com.org.demo.model.repositories.EquipmentRepository
import com.org.demo.utils.GeneralApiListner
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance

class EquipmentViewModel(appContext: Context) : BaseViewModel(appContext) {

    private val repository: EquipmentRepository by instance()
    var listner: GeneralApiListner? = null


    private val _equipmentnList = MutableLiveData<List<EquipmentModel?>>()
    val equipmentListLiveData: LiveData<List<EquipmentModel?>>
        get() = _equipmentnList

    fun getEquipmentList(/*pageSize:Int , pageNumber:Int*/) {
        try{
        listner?.onLoading()
        viewModelScope.launch {
            when (val response = repository.getEquipmentList()) {
                is ResultWrapper.NetworkError -> listner?.onNetworkError()
                is ResultWrapper.GenericError -> response.error?.let { listner?.onFailuer(it) }
                is ResultWrapper.Success -> {
                    _equipmentnList.value = response.value.results!!
                    listner?.onSuccess()
                }
            }
        }
        }catch (ex: ApiException){
            ex.message?.let {
                listner?.onFailuer(
                    it
                )
            }
        }catch (ex: NoInternetException){
            ex.message?.let {
                listner?.onFailuer(
                    it
                )
            }
        }
    }

}

