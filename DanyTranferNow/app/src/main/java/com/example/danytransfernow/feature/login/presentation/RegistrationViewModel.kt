package com.example.danytransfernow.feature.login.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.danytransfernow.MainApplication
import com.example.danytransfernow.feature.login.domain.usecase.LoginParams
import com.example.danytransfernow.feature.login.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(private val registerUseCase: RegisterUseCase) :
    ViewModel() {
    val isSuccess = MutableLiveData<Boolean>()

    init {
        isSuccess.value = false
    }

    fun register(loginParams: LoginParams) {
        registerUseCase.execute(loginParams,
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