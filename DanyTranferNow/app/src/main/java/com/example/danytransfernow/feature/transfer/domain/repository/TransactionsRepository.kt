package com.example.danytransfernow.feature.login.domain.repository

import com.example.danytransfernow.core.api.ApiResponse
import com.example.danytransfernow.feature.login.domain.usecase.TransferReqParam
import com.example.danytransfernow.feature.transfer.domain.model.Balance
import com.example.danytransfernow.feature.transfer.domain.model.Payees
import com.example.danytransfernow.feature.transfer.domain.model.TransactionList
import io.reactivex.Single

/**
 * To make an interaction between [TransactionsRepository] & UseCase
 * */
interface TransactionsRepository {

    fun getTransactionList(): Single<TransactionList>
    fun getPayeees(): Single<Payees>
    fun getBalance(): Single<Balance>
    fun transfer(params: TransferReqParam): Single<ApiResponse>
}