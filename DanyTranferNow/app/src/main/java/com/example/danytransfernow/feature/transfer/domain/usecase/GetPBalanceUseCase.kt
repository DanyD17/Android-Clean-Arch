package com.example.danytransfernow.feature.login.domain.usecase

import com.example.danytransfernow.core.useCase.SingleUseCase
import com.example.danytransfernow.feature.login.domain.repository.TransactionsRepository
import com.example.danytransfernow.feature.transfer.domain.model.Balance
import io.reactivex.Single
import javax.inject.Inject


class GetPBalanceUseCase @Inject constructor(private val repository: TransactionsRepository) :
    SingleUseCase<Balance, Any?>() {

    override fun buildUseCaseSingle(params: Any?): Single<Balance> {
        return repository.getBalance()
    }
}
