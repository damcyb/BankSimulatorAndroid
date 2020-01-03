package com.example.banksimulatorandroid.Model.Response

import com.google.gson.annotations.SerializedName

data class TransferRest (

    @SerializedName("date")
    val date: String,

    @SerializedName("firstName")
    val firstName: String,

    @SerializedName("lastName")
    val lastName: String,

    @SerializedName("accountNumber")
    val accountNumber: String,

    @SerializedName("transferredMoney")
    val transferredMoney: Double,

    @SerializedName("transferDirection")
    val transferDirection: String
)