package com.example.banksimulatorandroid.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.banksimulatorandroid.Model.Request.UserLoginRequestModel
import com.example.banksimulatorandroid.Model.Response.UserRest
import com.example.banksimulatorandroid.Model.UserRestService
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import javax.security.auth.callback.Callback

class LoginViewModel(application: Application): AndroidViewModel(application) {

    private val userRestService = UserRestService()

    fun loginUser(email: String, password: String) {
        getUserByEmailAndPassword(email, password)
    }

    private fun getUserByEmailAndPassword(email: String, password: String) {


        val requestCall = userRestService.getUserByEmailAndPassword(email, password)
        requestCall.enqueue(object : Callback, retrofit2.Callback<UserRest> {
            override fun onFailure(call: Call<UserRest>, t: Throwable) {
                println("FAILURE")
//                blogLoadError.value = true
//                loading.value = false
            }

            override fun onResponse(call: Call<UserRest>, response: Response<UserRest>) {
                val res = response.body()
                println(res)
                println("SUCCESS")
//                blogs.value = response.body()
//                blogLoadError.value = false
//                loading.value = false
            }

        })
    }
}