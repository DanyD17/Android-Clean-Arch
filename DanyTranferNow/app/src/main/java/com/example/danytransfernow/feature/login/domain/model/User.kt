package com.example.danytransfernow.feature.login.domain.model


data class User(
    var status: String,
    var error: String?,
    var token: String?, var username: String?, var accountNo: String?
)

data class UserLogin(
    var username: String, var password: String
)