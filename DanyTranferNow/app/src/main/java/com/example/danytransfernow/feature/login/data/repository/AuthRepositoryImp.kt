package com.example.danytransfernow.feature.login.data.repository

import com.example.danytransfernow.feature.login.data.source.remote.AuthDataSource
import com.example.danytransfernow.feature.login.domain.model.User
import com.example.danytransfernow.feature.login.domain.repository.AuthRepository
import io.reactivex.Single
import javax.inject.Inject


/**
 * This repository is responsible for
 * fetching data[User] from server or db
 * */
class AuthRepositoryImp @Inject constructor(
    private val authDataSource: AuthDataSource
) :
    AuthRepository {
    override fun login(username: String, password: String): Single<User> {
        return authDataSource.login(username = username, password = password)
    }

    override fun register(username: String, password: String): Single<User> {
        return authDataSource.register(username = username, password = password)
    }


}