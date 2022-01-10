package com.example.danytransfernow.feature.login.domain.usecase

import com.example.danytransfernow.core.api.ApiResponse
import com.example.danytransfernow.core.useCase.SingleUseCase
import com.example.danytransfernow.feature.login.domain.repository.TransactionsRepository
import io.reactivex.Single
import javax.inject.Inject


class TransferUseCase @Inject constructor(private val repository: TransactionsRepository) :
    SingleUseCase<ApiResponse, TransferReqParam>() {
    override fun buildUseCaseSingle(params: TransferReqParam): Single<ApiResponse> {
        return repository.transfer(params)
    }

}

data class TransferReqParam(
    val receipientAccountNo: String,
    val amount: Float,
    val description: String,
)