package com.samsul.githubuser.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_favorite")
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val image: String
)