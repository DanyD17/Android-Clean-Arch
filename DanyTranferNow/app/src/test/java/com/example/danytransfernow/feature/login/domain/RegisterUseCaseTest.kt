package com.example.danytransfernow.feature.login.domain

import com.example.danytransfernow.feature.login.domain.model.User
import com.example.danytransfernow.feature.login.domain.repository.AuthRepository
import com.example.danytransfernow.feature.login.domain.usecase.LoginParams
import com.example.danytransfernow.feature.login.domain.usecase.RegisterUseCase
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RegisterUseCaseTest {
    lateinit var registerUseCase: RegisterUseCase

    @Mock
    lateinit var authRepository: AuthRepository
    private lateinit var user: User
    private lateinit var error: User

    @Before
    @Throws(Exception::class)
    fun setUp() {
        authRepository = Mockito.mock(authRepository::class.java)
        registerUseCase = RegisterUseCase(authRepository)


        user = User(
            status = "success",
            error = null, token = "3r4i4i93394294nf", username = "dany", accountNo = "234242"
        )
        error = User(
            status = "failed",
            error = "invalid login credential",
            token = null, username = null, accountNo = null
        )
    }

    @Test
    fun testSuccessfulLogin() {
        Mockito.doReturn(Single.just(user)).`when`(authRepository).register("dany", "pass")
        var result = registerUseCase.buildUseCaseSingle(LoginParams("dany", "pass"))
        assert(!result.blockingGet().username.isNullOrEmpty())
    }

    @Test
    fun testUnSuccessfulLogin() {
        Mockito.doReturn(Single.just(error)).`when`(authRepository).register("dany", "pss")
        var result = registerUseCase.buildUseCaseSingle(LoginParams("dany", "pss"))
        assert(!result.blockingGet().username.isNullOrEmpty())
    }
}