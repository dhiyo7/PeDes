package com.plugin.pengaduandesa.presenters

import com.plugin.pengaduandesa.contracts.PengaduanActivityContract
import com.plugin.pengaduandesa.models.Category
import com.plugin.pengaduandesa.models.Pengaduan
import com.plugin.pengaduandesa.webservices.PengaduanAPI
import com.plugin.pengaduandesa.webservices.WrappedListResponse
import com.plugin.pengaduandesa.webservices.WrappedResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.sql.DriverManager.println

class CreatePengaduanActivityPresenter(v : PengaduanActivityContract.CreatePengaduanView?) : PengaduanActivityContract.CreatePengaduanInteractor {

    private var view : PengaduanActivityContract.CreatePengaduanView? = v
    private var api = PengaduanAPI.instance()

    override fun postComplaint(
        token: String,
        complaint_category_id: RequestBody,
        complaint_content: RequestBody,
        latitude: RequestBody,
        longitude: RequestBody,
        complaint_image: MultipartBody.Part
    ) {
        val request = api.postComplaint(token, complaint_category_id, complaint_content, latitude, longitude, complaint_image)

        request.enqueue(object : Callback<WrappedResponse<Pengaduan>>{
            override fun onResponse(
                call: Call<WrappedResponse<Pengaduan>>,
                response: Response<WrappedResponse<Pengaduan>>
            ) {
                if(response.isSuccessful){
                    val body = response.body()
                    if(body != null){
                        view?.showToast(body?.message!!)
                        view?.successPost()
                    }
                }else{
                    view?.showToast("Terjadi kesalahan")
                }
            }

            override fun onFailure(call: Call<WrappedResponse<Pengaduan>>, t: Throwable) {
                view?.showToast("Tidak bisa koneksi ke server")
                println("ERROR POST " + t.message)
                t.printStackTrace()
            }

        })
    }

    override fun getCategory(token: String) {
        val request = api.getCategory("Bearer "+token)
        request.enqueue(object : Callback<WrappedListResponse<Category>>{
            override fun onResponse(
                call: Call<WrappedListResponse<Category>>,
                response: Response<WrappedListResponse<Category>>
            ) {
                if(response.isSuccessful){
                    val body = response.body()
                    if(body != null){
                        view?.attachToSpinner(body.data)

                    }
                }else{
                    view?.showToast("Terjadi kesalahan")
                }
            }

            override fun onFailure(call: Call<WrappedListResponse<Category>>, t: Throwable) {
                view?.showToast("Tidak bisa koneksi ke server")
                println("ERROR CATEGORY "+ t.message)
            }

        })
    }

    override fun destroy() {
        view = null
    }
}