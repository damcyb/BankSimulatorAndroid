package com.example.banksimulatorandroid.Model.Request

class TransferMoneyRequestModel(
    val receiverFirstName: String,
    val receiverLastName: String,
    val receiverAccount: String,
    val transferredMoney: Double
)