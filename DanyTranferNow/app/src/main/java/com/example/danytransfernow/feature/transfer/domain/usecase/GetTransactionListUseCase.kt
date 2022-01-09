package com.example.danytransfernow.feature.login.domain.usecase

import com.example.danytransfernow.core.useCase.SingleUseCase
import com.example.danytransfernow.feature.login.domain.model.User
import com.example.danytransfernow.feature.login.domain.repository.TransactionsRepository
import io.reactivex.Single
import javax.inject.Inject


class GetTransactionListUseCase @Inject constructor(private val repository: TransactionsRepository) :
    SingleUseCase<User, LoginParams>() {

    override fun buildUseCaseSingle(params: LoginParams): Single<User> {
        return repository.login(username = params.username, password = params.password)
    }
}

