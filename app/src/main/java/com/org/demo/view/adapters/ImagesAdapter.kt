package com.org.demo.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.org.demo.R
import com.org.demo.databinding.ImagePagerItemBinding


class ImagesAdapter() : RecyclerView.Adapter<ImagesAdapter.OnBoardingViewHolder>() {

    private var list: List<String> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                                    R.layout.image_pager_item,
                                    parent,
                       false))
    }


    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.imagePagerItemBinding.url = list[position]
    }

    fun setItem(list: List<String>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    inner class OnBoardingViewHolder( val imagePagerItemBinding: ImagePagerItemBinding)
                                    : RecyclerView.ViewHolder(imagePagerItemBinding.root)
}