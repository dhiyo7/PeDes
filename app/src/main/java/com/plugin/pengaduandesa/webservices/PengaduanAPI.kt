package com.plugin.pengaduandesa.webservices

import com.google.gson.annotations.SerializedName
import com.plugin.pengaduandesa.models.User
import com.plugin.pengaduandesa.utils.PengaduanUtils
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

class PengaduanAPI {
    companion object{
        private var retrofit: Retrofit? = null
        private var okHttp: OkHttpClient? = OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).build()

        fun instance() : PengaduanAPIService = getClient().create(PengaduanAPIService::class.java)

        private fun getClient(): Retrofit {
            return if (retrofit== null){
                retrofit = Retrofit.Builder().baseUrl(PengaduanUtils.API_ENDPOINT).client(okHttp).addConverterFactory(GsonConverterFactory.create()).build()
                retrofit!!
            } else {
                retrofit!!
            }
        }
    }
}

interface PengaduanAPIService {
    @FormUrlEncoded
    @POST("/api/login")
    fun login(@Field("email") email: String? = null, @Field("password") password: String? = null):Call<WrappedResponse<User>>
}


data class WrappedResponse<T> (
        @SerializedName("message") var message : String?,
        @SerializedName("status") var status : Boolean,
        @SerializedName("data") var data : T?
){
    constructor() : this(null, false, null)
}

data class WrappedListResponse<T> (
        @SerializedName("message") var message : String?,
        @SerializedName("status") var status : Boolean,
        @SerializedName("results") var result : List<T>
){
    constructor() : this(null, false, listOf())

}