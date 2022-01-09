package com.example.danytransfernow.feature.transfer.domain.model

data class Transaction(
    val transactionId: String,
    val amount: Float,
    val transactionDate: String, val description: String, val transactionType: String,
    val receipient: Receipient
)

data class Receipient(
    val accountNo: String,
    val accountHolder: Float
)
