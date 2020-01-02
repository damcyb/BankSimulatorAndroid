package com.example.banksimulatorandroid.Model.Response

import com.google.gson.annotations.SerializedName

data class UserRest(

    @SerializedName("userId")
    val userId: String,

    @SerializedName("firstName")
    val firstName: String,

    @SerializedName("lastName")
    val lastName: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("balance")
    val balance: Double,

    @SerializedName("accountNumber")
    val accountNumber: String
    )