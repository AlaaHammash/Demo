package com.org.demo.view.activity

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.org.demo.R
import com.org.demo.databinding.ActivityEquipmentDetailsBinding
import com.org.demo.model.models.EquipmentModel
import com.org.demo.view.adapters.ImagesAdapter
import com.org.demo.viewModel.EquipmentViewModel
import org.kodein.di.KodeinAware

class EquipmentDetailsActivity : BaseActivity<EquipmentViewModel, ActivityEquipmentDetailsBinding>(),
    KodeinAware {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       val equipmentDetails = intent.getSerializableExtra("Item") as EquipmentModel?
        binding.model = equipmentDetails

        val adapter = ImagesAdapter()
        equipmentDetails?.image_urls?.let { adapter.setItem(it) }
        binding.photosViewpager.adapter = adapter
        binding.photosViewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

            }

        })
        TabLayoutMediator(binding.tabLayout,binding.photosViewpager) { tab, position -> }.attach()


    }


    override fun getViewModelClass(): Class<EquipmentViewModel> {
        return EquipmentViewModel::class.java
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_equipment_details
    }



}
