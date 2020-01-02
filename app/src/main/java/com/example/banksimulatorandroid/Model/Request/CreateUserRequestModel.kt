package com.example.banksimulatorandroid.Model.Request

data class CreateUserRequestModel (
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)