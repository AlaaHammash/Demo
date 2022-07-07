package com.org.demo.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.org.demo.R
import com.org.demo.databinding.ActivityMainBinding
import com.org.demo.model.models.EquipmentModel
import com.org.demo.utils.GeneralApiListner
import com.org.demo.utils.UiUtils
import com.org.demo.utils.openActivity
import com.org.demo.utils.toast
import com.org.demo.view.adapters.EquipmentsAdapter
import com.org.demo.viewModel.EquipmentViewModel
import koleton.api.hideSkeleton
import koleton.api.loadSkeleton
import java.util.ArrayList

class MainActivity : BaseActivity<EquipmentViewModel, ActivityMainBinding>(),
    GeneralApiListner {
    
    override fun getViewModelClass(): Class<EquipmentViewModel> {
        return EquipmentViewModel::class.java
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

    private lateinit var equipmentsAdapter: EquipmentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.myToolbar.title = "Demo";
    //    setSupportActionBar(binding.myToolbar);


        viewModel.listner = this

        viewModel.getEquipmentList()
        binding.rvEquipment.loadSkeleton()
        viewModel.equipmentListLiveData.observe(this, Observer {
            if (it.isNotEmpty()){
                binding.rvEquipment.hideSkeleton()
                binding.rvEquipment.layoutManager = LinearLayoutManager(this)
                equipmentsAdapter =
                    EquipmentsAdapter { item -> skipClick(item as EquipmentModel) }
                equipmentsAdapter.setItem(it as List<EquipmentModel>)
                binding.rvEquipment.adapter = equipmentsAdapter
            }
        })


    }

    private fun skipClick(item: EquipmentModel) {
        openActivity<EquipmentDetailsActivity>(){
            putExtra("Item", item)
        }
    }



    override fun onSuccess() {
        if (UiUtils.isProgressShowing()) UiUtils.dismissProccessDialog()
    }

    override fun onFailuer(message: String) {
        if (UiUtils.isProgressShowing()) UiUtils.dismissProccessDialog()
        toast(message)
    }

    override fun onLoading() {
     UiUtils.showProccesDialog(this, this)
    }


    override fun onNetworkError() {

    }

}