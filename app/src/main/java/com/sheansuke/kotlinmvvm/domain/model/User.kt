package com.sheansuke.kotlinmvvm.domain.model

import com.google.gson.Gson

data class User(
    var id: String? = null,
    var username: String? = null,
    var email: String? = null,
    var password: String? = null
) {
    fun toJson(): String = Gson().toJson(this)
    fun fromJson(user: String): User = Gson().fromJson(user, User::class.java)
}
