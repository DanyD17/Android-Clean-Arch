package com.example.danytransfernow.feature.transfer.domain.model

data class Transaction(
    val transactionId: String,
    val amount: Float,
    val transactionDate: String, val description: String?, val transactionType: String,
    val receipient: Receipient?,
    val sender: Sender?
)

data class Receipient(
    val accountHolder: String,
    val accountNo: String
)

data class Sender(
    val accountNo: String,
    val accountHolder: String
)