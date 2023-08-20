package com.sheansuke.kotlinmvvm.domain.model

import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

data class Post(
    val userId: String = "",
    val name: String = "",
    val description: String = "",
    val category: String = "",
    val imageUrl: String = ""
) {
    fun toJson(): String = Gson().toJson(
        Post(
            userId,
            name,
            description,
            description,
            imageUrl = URLEncoder.encode(
                imageUrl,
                StandardCharsets.UTF_8.toString()
            ),
        )
    )

    fun fromJson(post: String): Post = Gson().fromJson(post, Post::class.java)
}
