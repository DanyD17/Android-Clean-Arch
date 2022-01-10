package com.example.danytransfernow.feature.login.domain.usecase

import com.example.danytransfernow.core.useCase.SingleUseCase
import com.example.danytransfernow.feature.login.domain.repository.TransactionsRepository
import com.example.danytransfernow.feature.transfer.domain.model.TransactionList
import io.reactivex.Single
import javax.inject.Inject


class TransferUseCase @Inject constructor(private val repository: TransactionsRepository) :
    SingleUseCase<TransactionList, TransferReqParam>() {
    override fun buildUseCaseSingle(params: TransferReqParam): Single<TransactionList> {
        return repository.getTransactionList()
    }

}

data class TransferReqParam(
    val receipientAccountNo: String,
    val amount: Float,
    val description: String,
)