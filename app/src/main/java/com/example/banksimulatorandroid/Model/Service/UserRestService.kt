package com.example.banksimulatorandroid.Model.Service

import com.example.banksimulatorandroid.Model.Api.UserRestServiceApi
import com.example.banksimulatorandroid.Model.Request.CreateUserRequestModel
import com.example.banksimulatorandroid.Model.Request.UserLoginRequestModel
import com.example.banksimulatorandroid.Model.Response.UserRest
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

    fun postNewUser(firstName: String, lastName: String, email: String, password: String): Call<UserRest> {
        val requestBody = CreateUserRequestModel(firstName, lastName, email, password)
        return api.createUser(requestBody)
    }
}