package com.org.demo.model.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Keep
data class EquipmentModel(
    @SerializedName("created_at")
    val created_at: String = "",
    @SerializedName("price")
    val price: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("uid")
    val uid: String = "",
    @SerializedName("image_ids")
    var image_ids: List<String>? = null,
    @SerializedName("image_urls_thumbnails")
    var image_urls_thumbnails: List<String>? = null,
    @SerializedName("image_urls")
    var image_urls: List<String>? = null,
) : Serializable
