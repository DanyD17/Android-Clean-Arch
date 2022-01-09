package com.example.danytransfernow.feature.login.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.danytransfernow.MainApplication
import com.example.danytransfernow.feature.login.domain.usecase.LoginParams
import com.example.danytransfernow.feature.login.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {
    val isSuccess = MutableLiveData<Boolean>()

    init {
        isSuccess.value = false
    }

    fun login(loginParams: LoginParams) {
        loginUseCase.execute(loginParams,
            onSuccess = {
                MainApplication.user = it
                isSuccess.value = true
            },
            onError = {
                isSuccess.value = false
            }
        )
    }
}