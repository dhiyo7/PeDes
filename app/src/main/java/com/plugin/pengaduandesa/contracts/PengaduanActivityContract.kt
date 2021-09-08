package com.plugin.pengaduandesa.contracts

import com.plugin.pengaduandesa.models.Category
import com.plugin.pengaduandesa.models.Pengaduan
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface PengaduanActivityContract {
    /* Approved */
    interface View {
        fun attachToRecycler(aduan : List<Pengaduan>)
        fun isLoading(state : Boolean)
        fun toast(message : String)
        fun emptyData()
    }
    interface Interactor{
        fun destroy()
        fun allDataWaiting(token : String)
        fun allDataApproved(token : String)
        fun allDataDecline(token : String)
        fun allDataFinished(token : String)
        fun complaintByUser(token : String)
    }

    interface CreatePengaduanView {
        fun attachToSpinner(category: List<Category>)
        fun showToast(message : String)
        fun successPost();
    }

    interface CreatePengaduanInteractor {
        fun postComplaint(token: String, complaint_category_id: RequestBody, complaint_content: RequestBody, latitude: RequestBody, longitude: RequestBody, complaint_image: MultipartBody.Part)
        fun getCategory(token : String)
        fun destroy()
    }
}