package com.example.danytransfernow.feature.login.domain.usecase

import com.example.danytransfernow.core.useCase.SingleUseCase
import com.example.danytransfernow.feature.login.domain.model.User
import com.example.danytransfernow.feature.login.domain.repository.AuthRepository
import io.reactivex.Single
import javax.inject.Inject


class RegisterUseCase  @Inject constructor(private val repository: AuthRepository) :
    SingleUseCase<User, LoginParams>() {

    override fun buildUseCaseSingle(params: LoginParams): Single<User> {
        return repository.register(username = params.username, password = params.password)
    }
}
