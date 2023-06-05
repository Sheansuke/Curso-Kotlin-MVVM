package com.sheansuke.kotlinmvvm.domain.model

import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

data class User(
    var id: String? = null,
    var image: String? = null,
    var username: String? = null,
    var email: String? = null,
    var password: String? = null
) {
    fun toJson(): String = Gson().toJson(User(
        id,
        image = if (image != null) URLEncoder.encode(image, StandardCharsets.UTF_8.toString()) else null,
        username,
        email,
        password
    ))
    fun fromJson(user: String): User = Gson().fromJson(user, User::class.java)
}
