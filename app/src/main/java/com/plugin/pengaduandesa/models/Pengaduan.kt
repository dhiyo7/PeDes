package com.plugin.pengaduandesa.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pengaduan(
    @SerializedName("id") var id: String? = null,
    @SerializedName("complaint_category_id") var complaint_category_id: String? = null,
    @SerializedName("complaint_content") var complaint_content: String? = null,
    @SerializedName("user_id") var user_id: String? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("complaint_image") var complaint_image: String? = null,
) : Parcelable