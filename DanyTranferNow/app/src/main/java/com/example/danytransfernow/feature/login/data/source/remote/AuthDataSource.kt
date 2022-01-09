package com.example.danytransfernow.feature.login.data.source.remote

import com.example.danytransfernow.core.api.RetrofitService
import com.example.danytransfernow.feature.login.domain.model.User
import com.example.danytransfernow.feature.login.domain.model.UserLogin
import io.reactivex.Single
import javax.inject.Inject


/**
 * This repository is responsible for
 * fetching data[User] from server
 * */
abstract class AuthDataSource() {
    abstract fun login(username: String, password: String): Single<User>
    abstract fun register(username: String, password: String): Single<User>

}

class AuthDataSourceImp @Inject constructor(
    private val retrofitService: RetrofitService
) : AuthDataSource() {


    override fun login(username: String, password: String): Single<User> {
        return retrofitService.login(UserLogin(username =username, password= password))
    }

    override fun register(username: String, password: String): Single<User> {
        return retrofitService.register(username, password)
    }


}
