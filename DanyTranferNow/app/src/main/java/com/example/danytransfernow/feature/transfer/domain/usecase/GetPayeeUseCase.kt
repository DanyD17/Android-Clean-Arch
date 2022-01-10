package com.example.danytransfernow.feature.login.domain.usecase

import com.example.danytransfernow.core.useCase.SingleUseCase
import com.example.danytransfernow.feature.login.domain.repository.TransactionsRepository
import com.example.danytransfernow.feature.transfer.domain.model.Payees
import io.reactivex.Single
import javax.inject.Inject


class GetPayeeUseCase @Inject constructor(private val repository: TransactionsRepository) :
    SingleUseCase<Payees, Any?>() {


    override fun buildUseCaseSingle(params: Any?): Single<Payees> {
        return repository.getPayeees()
    }
}
