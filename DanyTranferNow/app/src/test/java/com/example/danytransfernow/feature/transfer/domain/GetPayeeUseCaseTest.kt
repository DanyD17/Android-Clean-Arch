package com.example.danytransfernow.feature.transfer.domain

import com.example.danytransfernow.feature.login.domain.repository.TransactionsRepository
import com.example.danytransfernow.feature.login.domain.usecase.GetPayeeUseCase
import com.example.danytransfernow.feature.transfer.domain.model.Payee
import com.example.danytransfernow.feature.transfer.domain.model.Payees
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetPayeeUseCaseTest {
    lateinit var getPayeeUseCase: GetPayeeUseCase


    @Mock
    lateinit var transactionsRepository: TransactionsRepository


    private lateinit var payees: Payees
    private lateinit var noPayee: Payees

    @Before
    @Throws(Exception::class)
    fun setUp() {
        transactionsRepository = Mockito.mock(TransactionsRepository::class.java)
        //  authDataSource = Mockito.mock(AuthDataSource::class.java)
        getPayeeUseCase = GetPayeeUseCase(transactionsRepository)


        payees = Payees(
            status = "success",
            data = listOf(Payee(id = "12323", name = "jack", accountNo = "234234-343"))

        )
        noPayee = Payees(
            status = "success",
            data = listOf()
        )
    }

    @Test
    fun testSuccessfulPayees() {
        Mockito.doReturn(Single.just(payees)).`when`(transactionsRepository).getPayeees()
        var result = getPayeeUseCase.buildUseCaseSingle(null)
        assert(!result.blockingGet().data.isNullOrEmpty())
    }

    @Test
    fun testNoPayee() {
        Mockito.doReturn(Single.just(noPayee)).`when`(transactionsRepository).getPayeees()
        var result = getPayeeUseCase.buildUseCaseSingle(null)
        assert(result.blockingGet().data.isEmpty())
    }
}