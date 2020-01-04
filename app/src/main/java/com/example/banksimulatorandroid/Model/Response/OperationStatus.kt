package com.example.banksimulatorandroid.Model.Response

import com.google.gson.annotations.SerializedName

data class OperationStatus(

    @SerializedName("operationResult")
    val operationResult: String,

    @SerializedName("operationName")
    val operationName: String
)