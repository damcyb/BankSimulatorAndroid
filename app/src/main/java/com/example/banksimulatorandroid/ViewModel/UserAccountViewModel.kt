package com.example.banksimulatorandroid.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.banksimulatorandroid.Model.Response.TransferRest
import com.example.banksimulatorandroid.Model.Service.TransferRestService
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class UserAccountViewModel(application: Application): AndroidViewModel(application) {

    val transferList = MutableLiveData<List<TransferRest>>()
    val loading = MutableLiveData<Boolean>()
    val transferListError = MutableLiveData<Boolean>()

    val transferRestService = TransferRestService()

    fun refresh(userId: String) {
        fetchTransfers(userId)
    }

    private fun fetchTransfers(userId: String) {
        val requestCall = transferRestService.getUserTransfers(userId)
        requestCall.enqueue(object : Callback, retrofit2.Callback<List<TransferRest>> {
            override fun onFailure(call: Call<List<TransferRest>>, t: Throwable) {
                println("FAILURE")
                loading.value = false
                transferListError.value = true
            }

            override fun onResponse(
                call: Call<List<TransferRest>>,
                response: Response<List<TransferRest>>
            ) {
                println("SUCCESS")
                transferList.value = response.body()
                loading.value = false
                transferListError.value = false
            }
        })
    }
}