package com.plugin.pengaduandesa.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pengaduan(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("complaint_category") var complaint_category: String? = null,
    @SerializedName("complaint_content") var complaint_content: String? = null,
    @SerializedName("user") var user: String? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("complaint_image") var complaint_image: String? = null,
    @SerializedName("latitude") var latitude: String? = null,
    @SerializedName("longitude") var longitude: String? = null,
    @SerializedName("created_at") var created_at: String? = null,
    @SerializedName("updated_at") var updated_at: String? = null,
) : Parcelable