package com.example.danytransfernow.feature.login.domain.repository

import com.example.danytransfernow.feature.login.domain.model.User
import io.reactivex.Single

/**
 * To make an interaction between [TransactionsRepository] & UseCase
 * */
interface TransactionsRepository {

    fun login(username: String, password: String): Single<User>
    fun register(username: String, password: String): Single<User>
}