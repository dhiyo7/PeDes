package com.plugin.pengaduandesa.webservices

import com.google.gson.annotations.SerializedName
import com.plugin.pengaduandesa.models.Category
import com.plugin.pengaduandesa.models.Pengaduan
import com.plugin.pengaduandesa.models.User
import com.plugin.pengaduandesa.utils.PengaduanUtils
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.*
import java.util.concurrent.TimeUnit

class PengaduanAPI {
    companion object {
        private var retrofit: Retrofit? = null
        private var okHttp: OkHttpClient? =
            OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).build()

        fun instance(): PengaduanAPIService = getClient().create(PengaduanAPIService::class.java)

        private fun getClient(): Retrofit {
            return if (retrofit == null) {
                retrofit = Retrofit.Builder().baseUrl(PengaduanUtils.API_ENDPOINT).client(okHttp)
                    .addConverterFactory(GsonConverterFactory.create()).build()
                retrofit!!
            } else {
                retrofit!!
            }
        }
    }
}

interface PengaduanAPIService {
    @FormUrlEncoded
    @POST("api/login")
    fun login(
        @Field("email") email: String? = null,
        @Field("password") password: String? = null
    ): Call<WrappedResponse<User>>

    @FormUrlEncoded
    @POST("api/save-token")
    fun saveDeviceToken(
        @Header("Authorization") token : String,
        @Field("device_token") device_token : String
    ) : Call<WrappedResponse<String>>

    @FormUrlEncoded
    @POST("api/register")
    fun register(
        @Field("NIK") nik: String? = null,
        @Field("email") email: String? = null,
        @Field("password") password: String? = null,
        @Field("confirm_password") confirm_password: String? = null
    ): Call<WrappedResponse<User>>

    @GET("api/complaint/approved")
    fun getComplaintApproved(@Header("Authorization") token : String): Call<WrappedListResponse<Pengaduan>>

    @GET("api/complaint/decline")
    fun getComplaintDecline(@Header("Authorization") token : String): Call<WrappedListResponse<Pengaduan>>

    @GET("api/complaint/waiting")
    fun getComplaintWaiting(@Header("Authorization") token : String): Call<WrappedListResponse<Pengaduan>>

    @GET("api/complaint/finished")
    fun getComplaintFinished(@Header("Authorization") token : String): Call<WrappedListResponse<Pengaduan>>

    @GET("api/complaint/by-user")
    fun getComplaintByUser(@Header("Authorization") token : String): Call<WrappedListResponse<Pengaduan>>

    @GET("api/complaint-category")
    fun getCategory(
        @Header("Authorization") token : String
    ) : Call<WrappedListResponse<Category>>

    @Multipart
    @POST("api/complaint")
    fun postComplaint(
        @Header("Authorization") token : String,
        @Part("complaint_category_id") complaint_category_id : RequestBody,
        @Part("complaint_content") complaint_content : RequestBody,
        @Part("latitude") latitude : RequestBody,
        @Part("longitude") longitude : RequestBody,
        @Part complaint_image_id: MultipartBody.Part
    ) : Call<WrappedResponse<Pengaduan>>
}


data class WrappedResponse<T>(
    @SerializedName("message") var message: String?,
    @SerializedName("status") var status: Int?,
    @SerializedName("data") var data: T?
) {
    constructor() : this(null, null, null)
}

data class WrappedListResponse<T>(
    @SerializedName("message") var message: String,
    @SerializedName("status") var status: Int,
    @SerializedName("data") var data: List<T>
)