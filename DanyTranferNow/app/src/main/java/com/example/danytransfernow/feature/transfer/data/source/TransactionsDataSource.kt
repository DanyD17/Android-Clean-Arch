package com.example.danytransfernow.feature.login.data.source.remote

import com.example.danytransfernow.core.api.ApiResponse
import com.example.danytransfernow.core.api.RetrofitService
import com.example.danytransfernow.feature.login.domain.model.User
import com.example.danytransfernow.feature.login.domain.usecase.TransferReqParam
import com.example.danytransfernow.feature.transfer.domain.model.Balance
import com.example.danytransfernow.feature.transfer.domain.model.Payees
import com.example.danytransfernow.feature.transfer.domain.model.TransactionList
import io.reactivex.Single
import javax.inject.Inject


/**
 * This repository is responsible for
 * fetching data[User] from server
 * */
abstract class TransactionDataSource {
    abstract fun getTransactionList(): Single<TransactionList>
    abstract fun getPayeees(): Single<Payees>
    abstract fun getBalance(): Single<Balance>
    abstract fun transfer(params: TransferReqParam): Single<ApiResponse>
}

class TransactionSourceImp @Inject constructor(
    private val retrofitService: RetrofitService
) : TransactionDataSource() {
    override fun getTransactionList(): Single<TransactionList> {
        return retrofitService.getTransactions()
    }

    override fun getPayeees(): Single<Payees> {
        return retrofitService.getPayees()
    }

    override fun getBalance(): Single<Balance> {
        return retrofitService.getBalance()
    }

    override fun transfer(params: TransferReqParam): Single<ApiResponse> {
        return retrofitService.transfer(params)
    }


}
