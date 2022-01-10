package com.example.danytransfernow.feature.transfer.domain

import com.example.danytransfernow.feature.login.domain.repository.TransactionsRepository
import com.example.danytransfernow.feature.login.domain.usecase.GetTransactionListUseCase
import com.example.danytransfernow.feature.transfer.domain.model.Receipient
import com.example.danytransfernow.feature.transfer.domain.model.Transaction
import com.example.danytransfernow.feature.transfer.domain.model.TransactionList
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TransactionListUseCaseTest {
    lateinit var transactionListUseCase: GetTransactionListUseCase

    @Mock
    lateinit var transactionsRepository: TransactionsRepository
    private lateinit var transactionList: TransactionList


    @Before
    @Throws(Exception::class)
    fun setUp() {
        transactionsRepository = Mockito.mock(TransactionsRepository::class.java)
        transactionListUseCase = GetTransactionListUseCase(transactionsRepository)


        transactionList = TransactionList(
            "success",
            data = listOf(
                Transaction(
                    transactionId = "1234", amount = 1000F,
                    transactionDate = "2022-01-02T13:11:44.545Z",
                    transactionType = "transfer",
                    receipient = Receipient(accountHolder = "Sam", accountNo = "12331"),
                    sender = null, description = "text"
                )
            )
        )

    }

    @Test
    fun testTransactionListSuccess() {
        Mockito.doReturn(Single.just(transactionList)).`when`(transactionsRepository)
            .getTransactionList()
        var result = transactionListUseCase.buildUseCaseSingle(null)
        assert(result.blockingGet().data.isNotEmpty())

    }


}