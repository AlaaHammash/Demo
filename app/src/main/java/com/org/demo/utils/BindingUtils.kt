package com.org.demo.utils


import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide



@BindingAdapter("image")
fun loadImage(view: ImageView, url: String) {
    if(!url.isNullOrBlank()){
        try{
/*            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.drawable.ic_launcher_foreground)
            requestOptions.error(R.drawable.ic_launcher_foreground)*/
            Glide.with(view)
                .load(url)
               /* .apply(requestOptions)*/
                .into(view)
        }catch (ex:Exception){
        }
    }
}






