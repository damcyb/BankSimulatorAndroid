package com.example.banksimulatorandroid.Model.Api

import com.example.banksimulatorandroid.Model.Request.*
import com.example.banksimulatorandroid.Model.Response.OperationStatus
import com.example.banksimulatorandroid.Model.Response.UserRest
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface UserRestServiceApi {

    @POST("/users/login")
    fun loginUser(@Body userLoginDetails: UserLoginRequestModel): Call<UserRest>

    @POST("/users")
    fun createUser(@Body userCreateDetails: CreateUserRequestModel): Call<UserRest>

    @GET("/users/{userId}")
    fun getUser(@Path("userId") userId: String): Call<UserRest>

    @PUT("/users/deposit/{userId}")
    fun depositMoney(@Body depositMoneyDetails: DepositMoneyRequestModel, @Path("userId") userId: String): Call<UserRest>

    @PUT("/users/withdraw/{userId}")
    fun withdrawMoney(@Body withdrawMoneyDetails: WithdrawMoneyRequestModel, @Path("userId") userId: String): Call<UserRest>

    @PUT("users/transfer/{userId}")
    fun transferMoney(@Body transferMoneyDetails: TransferMoneyRequestModel, @Path("userId") userId: String): Call<UserRest>

    @PUT("users/{userId}")
    fun updateUser(@Body updateUserDetails: CreateUserRequestModel, @Path("userId") userId: String): Call<UserRest>

    @DELETE("/users/{userId}")
    fun deleteUser(@Path("userId") userId: String): Call<OperationStatus>

//    @POST("/users/login")
//    fun loginUser(@Body requestBody: Use): Call<ResponseBody>

//    @POST("blog")
//    fun addBlog(@Body newBlog: Blog): Call<Blog>
//
//    @PUT("blog/{id}")
//    fun updateBlog(@Path("id") id: Int, @Body updatedBlog: Blog): Call<Blog>
//
//    @DELETE("blog/{id}")
//    fun deleteBlog(@Path("id") id: Int): Call<String>
}