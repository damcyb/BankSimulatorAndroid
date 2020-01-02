package com.example.banksimulatorandroid.Model.Response

import com.google.gson.annotations.SerializedName

data class TransferRest (

    @SerializedName("date")
    val date: String,

    @SerializedName("receiverFirstName")
    val receiverFirstName: String,

    @SerializedName("receiverLastName")
    val receiverLastName: String,

    @SerializedName("receiverAccountNumber")
    val receiverAccountNumber: String,

    @SerializedName("transferredMoney")
    val transferredMoney: Double
)