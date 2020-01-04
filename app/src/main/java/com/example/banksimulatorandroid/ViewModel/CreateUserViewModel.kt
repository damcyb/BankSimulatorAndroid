package com.example.banksimulatorandroid.ViewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.banksimulatorandroid.Constants.CREATE_USER_DUPLICATE_ERROR
import com.example.banksimulatorandroid.Constants.NETWORK_ERROR
import com.example.banksimulatorandroid.Model.Response.UserRest
import com.example.banksimulatorandroid.Model.Service.UserRestService
import retrofit2.Call
import retrofit2.Response
import java.lang.RuntimeException
import javax.security.auth.callback.Callback

class CreateUserViewModel (application: Application): AndroidViewModel(application) {

    private val userRestService = UserRestService()
    val userRest = MutableLiveData<UserRest>()

    fun createUser(firstName: String, lastName: String, email: String, password: String) {
        postNewUser(firstName, lastName, email, password)
    }

    private fun postNewUser(firstName: String, lastName: String, email: String, password: String){

        val requestCall = userRestService.postNewUser(firstName, lastName, email, password)
        requestCall.enqueue(object : Callback, retrofit2.Callback<UserRest> {
            override fun onFailure(call: Call<UserRest>, t: Throwable) {
                Toast.makeText(getApplication(), NETWORK_ERROR, Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call<UserRest>, response: Response<UserRest>) {
                try {
                    userRest.value = response.body()!!
                } catch (e: RuntimeException){
                    Toast.makeText(getApplication(), CREATE_USER_DUPLICATE_ERROR, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}