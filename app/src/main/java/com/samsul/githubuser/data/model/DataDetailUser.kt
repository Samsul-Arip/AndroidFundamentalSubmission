package com.samsul.githubuser.data.model

import com.google.gson.annotations.SerializedName

data class DataDetailUser(
    @SerializedName("login") val username: String,
    @SerializedName("name") val name: String,
    @SerializedName("avatar_url") val image: String,
    @SerializedName("location") val location: String,
    @SerializedName("followers") val followers: String,
    @SerializedName("following") val following: String
)
