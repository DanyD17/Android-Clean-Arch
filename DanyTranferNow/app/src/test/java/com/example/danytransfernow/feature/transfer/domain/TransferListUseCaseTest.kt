package com.example.danytransfernow.feature.transfer.domain

import com.example.danytransfernow.feature.login.domain.repository.TransactionsRepository
import com.example.danytransfernow.feature.login.domain.usecase.TransferUseCase
import com.example.danytransfernow.feature.transfer.domain.model.TransactionList
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TransferListUseCaseTest {
    lateinit var transferUseCase: TransferUseCase

    @Mock
    lateinit var transactionsRepository: TransactionsRepository
    private lateinit var transactionList: TransactionList


    @Before
    @Throws(Exception::class)
    fun setUp() {
        transactionsRepository = Mockito.mock(TransactionsRepository::class.java)
        transferUseCase = TransferUseCase(transactionsRepository)


    }

    @Test
    fun testTransactionListSuccess() {

    }


}