package com.example.banksimulatorandroid.Model

import com.example.banksimulatorandroid.Model.Request.UserLoginRequestModel
import com.example.banksimulatorandroid.Model.Response.UserRest
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class UserRestService {

    //private val BASE_URL = "http://192.168.1.11:8080"
    private val BASE_URL = "http://10.0.2.2:8080/"
    private val api: UserRestServiceApi

    init {
        api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(UserRestServiceApi::class.java)
    }

    fun getUserByEmailAndPassword(email: String, password: String): Call<UserRest> {
        val requestBody = UserLoginRequestModel(email, password)
        return api.loginUser(requestBody)
    }
}