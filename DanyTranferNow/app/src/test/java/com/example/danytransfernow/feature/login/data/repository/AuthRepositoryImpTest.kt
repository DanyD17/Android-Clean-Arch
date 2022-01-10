package com.example.danytransfernow.feature.login.data.repository

import com.example.danytransfernow.core.api.RetrofitService
import com.example.danytransfernow.feature.login.data.source.remote.AuthDataSource
import com.example.danytransfernow.feature.login.data.source.remote.AuthDataSourceImp
import com.example.danytransfernow.feature.login.domain.model.User
import com.example.danytransfernow.feature.login.domain.model.UserLogin
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class AuthRepositoryImpTest {
    lateinit var authDataSourceImp: AuthDataSourceImp

    @Mock
    lateinit var authDataSource: AuthDataSource

    @Mock
    lateinit var retrofitService: RetrofitService
    private lateinit var user: User
    private lateinit var error: User

    @Before
    @Throws(Exception::class)
    fun setUp() {
        retrofitService = mock(RetrofitService::class.java)
        authDataSource = mock(AuthDataSource::class.java)
        authDataSourceImp = AuthDataSourceImp(retrofitService)


        user = User(
            status = "success",
            error = null, token = "dveregedvdv", username = "dany", accountNo = "234242"
        )
        error = User(
            status = "failed",
            error = "invalid login credential",
            token = null, username = null, accountNo = null
        )
    }

    @Test
    fun testSuccessfulLogin() {
        doReturn(Single.just(user)).`when`(retrofitService).login(UserLogin("dany", "pass"))
        var result = authDataSourceImp.login("dany", "pass")
        assert(!result.blockingGet().username.isNullOrEmpty())
    }

    @Test
    fun testUnSuccessfulLogin() {
        doReturn(Single.just(error)).`when`(retrofitService).login(UserLogin("dany", "pss"))
        var result = authDataSourceImp.login("dany", "pss")
        assert(result.blockingGet().status == "failed")
    }
}