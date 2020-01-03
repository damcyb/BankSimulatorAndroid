package com.example.banksimulatorandroid.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.banksimulatorandroid.Model.Response.UserRest
import com.example.banksimulatorandroid.Model.Service.UserRestService
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class DepositMoneyViewModel(application: Application): AndroidViewModel(application) {

    private val userRestService = UserRestService()
    val userRest = MutableLiveData<UserRest>()

    fun deposit(money: Double, userId: String) {
        depositMoney(money, userId)
    }

    private fun depositMoney(money: Double, userId: String) {
        val requestCall = userRestService.depositMoneyInUserAccount(money, userId)
        requestCall.enqueue(object : Callback, retrofit2.Callback<UserRest> {
            override fun onFailure(call: Call<UserRest>, t: Throwable) {
                println("FAILURE")
            }

            override fun onResponse(call: Call<UserRest>, response: Response<UserRest>) {
                userRest.value = response.body()
                println("SUCCESS")
            }
        })
    }
}