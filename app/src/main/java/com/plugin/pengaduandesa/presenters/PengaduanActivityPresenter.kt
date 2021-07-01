package com.plugin.pengaduandesa.presenters

import com.plugin.pengaduandesa.contracts.PengaduanActivityContract
import com.plugin.pengaduandesa.models.Pengaduan
import com.plugin.pengaduandesa.webservices.PengaduanAPI
import com.plugin.pengaduandesa.webservices.WrappedListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PengaduanActivityPresenter(v: PengaduanActivityContract.View?) :
    PengaduanActivityContract.Interactor {

    private var view: PengaduanActivityContract.View? = v
    private var api = PengaduanAPI.instance()
    override fun allDataWaiting(token: String) {
        api.getComplaintWaiting(token)
            .enqueue(object : Callback<WrappedListResponse<Pengaduan>> {
                override fun onResponse(
                    call: Call<WrappedListResponse<Pengaduan>>,
                    response: Response<WrappedListResponse<Pengaduan>>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null && body.status == 200) {
                            view?.attachToRecycler(body.data)
                            println("DATA WAITING : ${body.data}")
                        } else {
                            view?.toast("ada yang tidak beres")
                        }
                    }
                }

                override fun onFailure(call: Call<WrappedListResponse<Pengaduan>>, t: Throwable) {
                    println("Log: ${t.message} ")
                    view?.toast("Cannot connect to server")
                }
            })
    }

    override fun allDataApproved(token: String) {
        api.getComplaintApproved(token)
            .enqueue(object : Callback<WrappedListResponse<Pengaduan>> {
                override fun onResponse(
                    call: Call<WrappedListResponse<Pengaduan>>,
                    response: Response<WrappedListResponse<Pengaduan>>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null && body.status == 200) {
                            view?.attachToRecycler(body.data)
                            println("DATA APPROVE : ${body.data}")
                        } else {
                            view?.toast("ada yang tidak beres")
                        }
                    }
                }

                override fun onFailure(call: Call<WrappedListResponse<Pengaduan>>, t: Throwable) {
                    println("Log: ${t.message} ")
                    view?.toast("Cannot connect to server")
                }

            })
    }

    override fun allDataDecline(token: String) {
        api.getComplaintDecline(token)
            .enqueue(object : Callback<WrappedListResponse<Pengaduan>> {
                override fun onResponse(
                    call: Call<WrappedListResponse<Pengaduan>>,
                    response: Response<WrappedListResponse<Pengaduan>>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null && body.status == 200) {
                            view?.attachToRecycler(body.data)
                            println("DATA DECLINE : ${body.data}")
                        } else {
                            view?.toast("ada yang tidak beres")
                        }
                    }
                }

                override fun onFailure(call: Call<WrappedListResponse<Pengaduan>>, t: Throwable) {
                    println("Log: ${t.message} ")
                    view?.toast("Cannot connect to server")
                }

            })
    }

    override fun destroy() {
        view = null
    }
}