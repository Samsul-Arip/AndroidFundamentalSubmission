package com.samsul.githubuser.data.model

import com.google.gson.annotations.SerializedName

class DataSearchUser {

    data class responseUser(
        @SerializedName("items") val listItems : ArrayList<dataUser>
    )
    data class dataUser(
        @SerializedName("login") val name: String?,
        @SerializedName("id") val id: String?,
        @SerializedName("avatar_url") val image: String?
    )
}