package com.example.danytransfernow.feature.login.domain.usecase

import com.example.danytransfernow.core.useCase.SingleUseCase
import com.example.danytransfernow.feature.login.domain.repository.TransactionsRepository
import com.example.danytransfernow.feature.transfer.domain.model.TransactionList
import io.reactivex.Single
import javax.inject.Inject


class GetTransactionListUseCase @Inject constructor(private val repository: TransactionsRepository) :
    SingleUseCase<TransactionList, Any?>() {
    override fun buildUseCaseSingle(params: Any?): Single<TransactionList> {
        return repository.getTransactionList()
    }

}
