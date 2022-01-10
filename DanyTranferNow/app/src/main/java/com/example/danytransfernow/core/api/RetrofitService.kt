package com.example.danytransfernow.core.api

import com.example.danytransfernow.feature.login.domain.model.User
import com.example.danytransfernow.feature.login.domain.model.UserLogin
import com.example.danytransfernow.feature.login.domain.usecase.TransferReqParam
import com.example.danytransfernow.feature.transfer.domain.model.Balance
import com.example.danytransfernow.feature.transfer.domain.model.Payees
import com.example.danytransfernow.feature.transfer.domain.model.TransactionList
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetrofitService {

    @POST("login")
    fun login(
        @Body login: UserLogin
    ): Single<User>

    @POST("register")
    @Headers("No-Authentication: true")
    fun register(
        @Body login: UserLogin
    ): Single<User>

    @GET("balance")
    fun getBalance(): Single<Balance>

    @GET("payees")
    fun getPayees(): Single<Payees>

    @GET("transactions")
    fun getTransactions(): Single<TransactionList>

    @POST("transfer")
    fun transfer(@Body pay: TransferReqParam): Single<ApiResponse>

}