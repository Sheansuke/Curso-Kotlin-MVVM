package com.sheansuke.kotlinmvvm.domain.model

data class User(
    var id: String? = null,
    var username: String,
    var email: String,
    var password: String? = null
)
