package com.example.pizzaapp3383.client

import com.example.pizzaapp3383.response.account.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {
        @FormUrlEncoded
        @POST("login")
        fun postLogin(
            @Field("username") username: String,
            @Field("password") password: String
        ): Call<LoginResponse>
}