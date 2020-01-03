package com.example.banksimulatorandroid.Model.Api

import com.example.banksimulatorandroid.Model.Request.CreateUserRequestModel
import com.example.banksimulatorandroid.Model.Request.DepositMoneyRequestModel
import com.example.banksimulatorandroid.Model.Request.UserLoginRequestModel
import com.example.banksimulatorandroid.Model.Request.WithdrawMoneyRequestModel
import com.example.banksimulatorandroid.Model.Response.UserRest
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface UserRestServiceApi {

    @POST("/users/login")
    fun loginUser(@Body userLoginDetails: UserLoginRequestModel): Call<UserRest>

    @POST("/users")
    fun createUser(@Body userLoginDetails: CreateUserRequestModel): Call<UserRest>

    @GET("/users/{userId}")
    fun getUser(@Path("userId") userId: String): Call<UserRest>

    @PUT("/users/deposit/{userId}")
    fun depositMoney(@Body depositMoneyDetails: DepositMoneyRequestModel, @Path("userId") userId: String): Call<UserRest>

    @PUT("/users/withdraw/{userId}")
    fun withdrawMoney(@Body withdrawMoneyDetails: WithdrawMoneyRequestModel, @Path("userId") userId: String): Call<UserRest>

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