package com.example.danytransfernow.feature.transfer.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.danytransfernow.feature.login.domain.usecase.GetPBalanceUseCase
import com.example.danytransfernow.feature.login.domain.usecase.GetTransactionListUseCase
import com.example.danytransfernow.feature.transfer.domain.model.Balance
import com.example.danytransfernow.feature.transfer.domain.model.TransactionList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val
    getTransactionListUseCase: GetTransactionListUseCase,
    private val getPBalanceUseCase: GetPBalanceUseCase
) : ViewModel() {

    val transactionList = MutableLiveData<TransactionList>()
    val balance = MutableLiveData<Balance>()
    val isSuccess = MutableLiveData<Boolean>()

    init {
        isSuccess.value = false
    }

    fun getTransactionList() {
        getTransactionListUseCase.execute(null,
            onSuccess = {
                isSuccess.value = true
                transactionList.value = it
                Log.d("DD", "transactionList ==${transactionList.value.toString()}")
            },
            onError = {
                isSuccess.value = false
            }
        )
    }

    fun getBalance() {
        getPBalanceUseCase.execute(null,
            onSuccess = {
                isSuccess.value = true
                balance.value = it
            },
            onError = {
                isSuccess.value = false
            }
        )
    }
}