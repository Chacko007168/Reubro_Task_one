package com.example.reburofirsttaskj


import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {
    @FormUrlEncoded
    @POST("login")
    fun userLogin(
        @Field("email_id") email:String,
        @Field("password") password:String,
        @Field("user_type") user_type:Int,
        @Field("provider_type") provider_type:Int
    ): retrofit2.Call<Response>
}