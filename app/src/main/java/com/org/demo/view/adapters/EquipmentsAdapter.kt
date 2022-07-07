package com.org.demo.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.org.demo.R
import com.org.demo.databinding.ItemEquipmentBinding
import com.org.demo.model.models.EquipmentModel

class EquipmentsAdapter(val skipClick: (Any) -> Unit) : RecyclerView.Adapter<EquipmentsAdapter.EquipmentItemViewHolder>() {

    private var list: List<EquipmentModel> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipmentItemViewHolder {
        return EquipmentItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                                    R.layout.item_equipment,
                                    parent,
                       false))
    }


    override fun onBindViewHolder(holder: EquipmentItemViewHolder, position: Int) {
        holder.equipmentItemBinding.model = list[position]
        holder.itemView.setOnClickListener {
            skipClick(list[position])
        }
    }


    override fun getItemCount(): Int = list.size

    fun setItem(list: List<EquipmentModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class EquipmentItemViewHolder( val equipmentItemBinding: ItemEquipmentBinding)
                                    : RecyclerView.ViewHolder(equipmentItemBinding.root)
}