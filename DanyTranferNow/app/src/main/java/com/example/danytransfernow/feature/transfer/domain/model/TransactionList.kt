package com.example.danytransfernow.feature.transfer.domain.model

data class TransactionList(
    val status: String,
    val data: List<Transaction>
)
