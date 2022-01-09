package com.example.danytransfernow.feature.login.data.repository

import com.example.danytransfernow.feature.login.data.source.remote.TransactionDataSource
import com.example.danytransfernow.feature.login.domain.model.User
import com.example.danytransfernow.feature.login.domain.repository.TransactionsRepository
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

    override fun login(username: String, password: String): Single<User> {
        return transactionDataSource.login(username = username ,password = password)
    }

    override fun register(username: String, password: String): Single <User> {
        return transactionDataSource.register(username = username ,password = password)
    }


}