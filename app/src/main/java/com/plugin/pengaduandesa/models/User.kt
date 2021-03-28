package com.plugin.pengaduandesa.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User (
        @SerializedName("id") var id : String? = null,
        @SerializedName("name") var name : String? = null,
        @SerializedName("username") var status : String? = null,
        @SerializedName("email") var email : String? = null,
        @SerializedName("email_verified_at") var email_verified_at : String? = null,
        @SerializedName("role_id") var role_id : String? = null,
        @SerializedName("token") var token : String? = null
) : Parcelable
