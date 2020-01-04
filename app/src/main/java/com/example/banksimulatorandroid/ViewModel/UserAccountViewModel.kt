package com.example.banksimulatorandroid.ViewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.banksimulatorandroid.Constants.NETWORK_ERROR
import com.example.banksimulatorandroid.Constants.TRANSFER_MONEY_ERROR
import com.example.banksimulatorandroid.Constants.USER_NOT_FOUND
import com.example.banksimulatorandroid.Model.Response.TransferRest
import com.example.banksimulatorandroid.Model.Response.UserRest
import com.example.banksimulatorandroid.Model.Service.TransferRestService
import com.example.banksimulatorandroid.Model.Service.UserRestService
import retrofit2.Call
import retrofit2.Response
import java.lang.RuntimeException
import javax.security.auth.callback.Callback

class UserAccountViewModel(application: Application): AndroidViewModel(application) {

    val userRest = MutableLiveData<UserRest>()
    val transferList = MutableLiveData<List<TransferRest>>()
    val loading = MutableLiveData<Boolean>()
    val transferListError = MutableLiveData<Boolean>()

    private val transferRestService = TransferRestService()
    private val userRestService = UserRestService()

    fun refresh(userId: String) {
        fetchTransfers(userId)
    }

    private fun fetchTransfers(userId: String) {
        val requestCall = transferRestService.getUserTransfers(userId)
        requestCall.enqueue(object : Callback, retrofit2.Callback<List<TransferRest>> {
            override fun onFailure(call: Call<List<TransferRest>>, t: Throwable) {
                Toast.makeText(getApplication(), NETWORK_ERROR, Toast.LENGTH_SHORT).show()
                loading.value = false
                transferListError.value = true
            }

            override fun onResponse(call: Call<List<TransferRest>>, response: Response<List<TransferRest>>) {
                transferList.value = response.body()
                loading.value = false
                transferListError.value = false
            }
        })
    }

    fun getUser(userId: String) {
        getUserById(userId)
    }

    private fun getUserById(userId: String) {

        val requestCall = userRestService.getUserById(userId)
        requestCall.enqueue(object : Callback, retrofit2.Callback<UserRest> {
            override fun onFailure(call: Call<UserRest>, t: Throwable) {
                Toast.makeText(getApplication(), NETWORK_ERROR, Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call<UserRest>, response: Response<UserRest>) {
                try {
                    userRest.value = response.body()!!
                } catch (e: RuntimeException) {
                    Toast.makeText(getApplication(), USER_NOT_FOUND, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}