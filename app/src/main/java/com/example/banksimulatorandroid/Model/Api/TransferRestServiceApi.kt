package com.example.banksimulatorandroid.Model.Api

import com.example.banksimulatorandroid.Model.Response.TransferRest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TransferRestServiceApi {

    @GET("users/transfer/{userId}")
    fun getUserTransfers(@Path("userId") userId: String): Call<List<TransferRest>>


}