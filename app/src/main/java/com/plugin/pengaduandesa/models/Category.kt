package com.plugin.pengaduandesa.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category (
    @SerializedName("id") var id : String,
    @SerializedName("complaint_category_name") var complaint_category_name : String,
    @SerializedName("created_at") var created_at : String,
    @SerializedName("updated_at") var updated_at : String
) : Parcelable {
    override fun toString(): String {
        return complaint_category_name
    }
}