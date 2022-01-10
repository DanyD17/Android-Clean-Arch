package com.example.danytransfernow.feature.transfer.domain

import com.example.danytransfernow.feature.login.domain.repository.TransactionsRepository
import com.example.danytransfernow.feature.login.domain.usecase.GetPBalanceUseCase
import com.example.danytransfernow.feature.transfer.domain.model.Balance
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetBalanceUseCaseTest {
    lateinit var getPBalanceUseCase: GetPBalanceUseCase

    @Mock
    lateinit var transactionsRepository: TransactionsRepository
    private lateinit var balance: Balance


    @Before
    @Throws(Exception::class)
    fun setUp() {
        transactionsRepository = Mockito.mock(TransactionsRepository::class.java)
        getPBalanceUseCase = GetPBalanceUseCase(transactionsRepository)


        balance = Balance(status = "success", balance = 1000.0F, accountNo = "123456")

    }

    @Test
    fun testBalance() {
        Mockito.doReturn(Single.just(balance)).`when`(transactionsRepository).getBalance()
        var result = getPBalanceUseCase.buildUseCaseSingle(null)
        assert(result.blockingGet().balance.toString() == "1000.0")
        assert(result.blockingGet().accountNo == "123456")
    }


}