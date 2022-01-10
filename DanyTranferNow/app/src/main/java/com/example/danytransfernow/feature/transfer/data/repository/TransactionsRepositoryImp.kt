package com.example.danytransfernow.feature.login.data.repository

import com.example.danytransfernow.core.api.ApiResponse
import com.example.danytransfernow.feature.login.data.source.remote.TransactionDataSource
import com.example.danytransfernow.feature.login.domain.repository.TransactionsRepository
import com.example.danytransfernow.feature.login.domain.usecase.TransferReqParam
import com.example.danytransfernow.feature.transfer.domain.model.Balance
import com.example.danytransfernow.feature.transfer.domain.model.Payees
import com.example.danytransfernow.feature.transfer.domain.model.TransactionList
import io.reactivex.Single
import javax.inject.Inject


/**
 * This repository is responsible for
 * fetching data  from server or db
 * */
class TransactionsRepositoryImp @Inject constructor(
    private val transactionDataSource: TransactionDataSource
) :
    TransactionsRepository {
    override fun getTransactionList(): Single<TransactionList> {
        return transactionDataSource.getTransactionList()
    }

    override fun getPayeees(): Single<Payees> {
        return transactionDataSource.getPayeees()
    }

    override fun getBalance(): Single<Balance> {
        return transactionDataSource.getBalance()
    }

    override fun transfer(params: TransferReqParam): Single<ApiResponse> {
        return transactionDataSource.transfer(params = params)
    }


}