package com.samsul.githubuser.data.model

import com.google.gson.annotations.SerializedName

data class DataFollow(
    @SerializedName("login") val username: String,
    @SerializedName("avatar_url") val image: String
)