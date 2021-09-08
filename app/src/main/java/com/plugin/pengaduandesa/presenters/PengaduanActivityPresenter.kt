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
        val request = api.getComplaintWaiting(token)
        view?.isLoading(true)
        request.enqueue(object : Callback<WrappedListResponse<Pengaduan>> {
                override fun onResponse(
                    call: Call<WrappedListResponse<Pengaduan>>,
                    response: Response<WrappedListResponse<Pengaduan>>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null && body.status == 200) {
                            if(body.data.size == 0){
                                view?.emptyData()
                            }else{
                                view?.attachToRecycler(body.data)
                            }
                        } else {
                            view?.toast("ada yang tidak beres")
                        }
                    }
                    view?.isLoading(false)
                }

                override fun onFailure(call: Call<WrappedListResponse<Pengaduan>>, t: Throwable) {
                    println("Log: ${t.message} ")
                    view?.toast("Cannot connect to server")
                    view?.isLoading(false)
                }
            })
    }

    override fun allDataApproved(token: String) {
        val request = api.getComplaintApproved(token)
        view?.isLoading(true)
        request.enqueue(object : Callback<WrappedListResponse<Pengaduan>> {
                override fun onResponse(
                    call: Call<WrappedListResponse<Pengaduan>>,
                    response: Response<WrappedListResponse<Pengaduan>>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null && body.status == 200) {
                            if(body.data.size == 0){
                                view?.emptyData()
                            }else{
                                view?.attachToRecycler(body.data)
                            }
                        } else {
                            view?.toast("ada yang tidak beres")
                        }
                    }
                    view?.isLoading(false)
                }

                override fun onFailure(call: Call<WrappedListResponse<Pengaduan>>, t: Throwable) {
                    println("Log: ${t.message} ")
                    view?.toast("Cannot connect to server")
                    view?.isLoading(false)
                }

            })
    }

    override fun allDataDecline(token: String) {
        val request = api.getComplaintDecline(token)
        view?.isLoading(true)
        request.enqueue(object : Callback<WrappedListResponse<Pengaduan>> {
                override fun onResponse(
                    call: Call<WrappedListResponse<Pengaduan>>,
                    response: Response<WrappedListResponse<Pengaduan>>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null && body.status == 200) {
                            if(body.data.size == 0){
                                view?.emptyData()
                            }else{
                                view?.attachToRecycler(body.data)
                            }
                        } else {
                            view?.toast("ada yang tidak beres")
                        }
                    }
                    view?.isLoading(false)
                }

                override fun onFailure(call: Call<WrappedListResponse<Pengaduan>>, t: Throwable) {
                    println("Log: ${t.message} ")
                    view?.toast("Cannot connect to server")
                    view?.isLoading(false)
                }

            })
    }

    override fun allDataFinished(token: String) {
        val request = api.getComplaintFinished(token)
        view?.isLoading(true)
        request.enqueue(object : Callback<WrappedListResponse<Pengaduan>> {
            override fun onResponse(
                call: Call<WrappedListResponse<Pengaduan>>,
                response: Response<WrappedListResponse<Pengaduan>>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null && body.status == 200) {
                        if(body.data.size == 0){
                            view?.emptyData()
                        }else{
                            view?.attachToRecycler(body.data)
                        }
                    } else {
                        view?.toast("ada yang tidak beres")
                    }
                }
                view?.isLoading(false)
            }

            override fun onFailure(call: Call<WrappedListResponse<Pengaduan>>, t: Throwable) {
                println("Log: ${t.message} ")
                view?.toast("Cannot connect to server")
                view?.isLoading(false)
            }
        })
    }

    override fun complaintByUser(token: String) {
        val request = api.getComplaintByUser(token)
        view?.isLoading(true)
        request.enqueue(object : Callback<WrappedListResponse<Pengaduan>> {
            override fun onResponse(
                call: Call<WrappedListResponse<Pengaduan>>,
                response: Response<WrappedListResponse<Pengaduan>>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null && body.status == 200) {
                        if(body.data.size == 0){
                            view?.emptyData()
                        }else{
                            view?.attachToRecycler(body.data)
                        }
                    } else {
                        view?.toast("ada yang tidak beres")
                    }
                }
                view?.isLoading(false)
            }

            override fun onFailure(call: Call<WrappedListResponse<Pengaduan>>, t: Throwable) {
                println("Log: ${t.message} ")
                view?.toast("Cannot connect to server")
                view?.isLoading(false)
            }

        })
    }

    override fun destroy() {
        view = null
    }
}