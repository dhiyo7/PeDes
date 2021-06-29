package com.plugin.pengaduandesa.contracts

import com.plugin.pengaduandesa.models.Pengaduan

interface PengaduanActivityContract {
    /* Approved */
    interface View {
        fun attachToRecycler(aduan : List<Pengaduan>)
        fun isLoading(state : Boolean)
        fun toast(message : String)
    }
    interface Interactor{
        fun destroy()
        fun allDataWaiting(token : String)
        fun allDataApproved(token : String)
        fun allDataDecline(token : String)
    }
}