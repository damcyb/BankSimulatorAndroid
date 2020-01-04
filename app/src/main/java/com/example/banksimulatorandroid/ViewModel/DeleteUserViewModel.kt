package com.example.banksimulatorandroid.ViewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.banksimulatorandroid.Constants.NETWORK_ERROR
import com.example.banksimulatorandroid.Model.Response.OperationStatus
import com.example.banksimulatorandroid.Model.Service.UserRestService
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class DeleteUserViewModel(application: Application): AndroidViewModel(application) {

    private val userRestService = UserRestService()
    val operationStatus = MutableLiveData<OperationStatus>()

    fun delete(userId: String) {
        deleteUser(userId)
    }

    private fun deleteUser(userId: String) {
        val requestCall = userRestService.deleteUser(userId)
        requestCall.enqueue(object : Callback, retrofit2.Callback<OperationStatus> {
            override fun onFailure(call: Call<OperationStatus>, t: Throwable) {
                Toast.makeText(getApplication(), NETWORK_ERROR, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<OperationStatus>, response: Response<OperationStatus>) {
                if(response.body()!!.operationResult.equals("SUCCESS")) {
                    operationStatus.value = response.body()
                }
            }
        })
    }
}
