package com.example.danytransfernow.feature.transfer.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.danytransfernow.feature.login.domain.usecase.GetPayeeUseCase
import com.example.danytransfernow.feature.login.domain.usecase.TransferReqParam
import com.example.danytransfernow.feature.login.domain.usecase.TransferUseCase
import com.example.danytransfernow.feature.transfer.domain.model.Payees
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransferViewModel @Inject constructor(
    private val getPayeeUseCase: GetPayeeUseCase,
    private val transferUseCase: TransferUseCase
) : ViewModel() {
    val payees = MutableLiveData<Payees>()
    val isTransfered = MutableLiveData<Boolean>()

    init {
        isTransfered.value = true
    }

    fun getPayees() {
        getPayeeUseCase.execute(null,
            onSuccess = {
                payees.value = it
                Log.d("DD", "payee ==${it.data}")
            },
            onError = {

            }
        )
    }

    fun transfer(param: TransferReqParam) {
        transferUseCase.execute(param,
            onSuccess = {
                isTransfered.value = true
                Log.d("DD", "payee ==${it}")
            },
            onError = {
                isTransfered.value = false
            }
        )
    }
}