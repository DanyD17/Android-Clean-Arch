package com.example.danytransfernow.core.api

import com.example.danytransfernow.feature.login.domain.model.User
import com.example.danytransfernow.feature.login.domain.model.UserLogin
import io.reactivex.Single

import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {

    @POST("login")
    fun login(
        @Body login: UserLogin
    ): Single<User>

    @POST("register")
    fun register(
        @Field("username") username: String,
        @Field("password") password: String,
    ): Single<User>

    @GET("balance")
    fun getBalance(): Single<List<User>>

    @GET("payees")
    fun getPayees(): Single<List<User>>

    @GET("transactions")
    fun getTransactions(): Single<List<User>>

    @POST("transfer")
    fun transfer(): Single<List<User>>

}