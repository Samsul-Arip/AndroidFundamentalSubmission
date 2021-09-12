package com.samsul.githubuser.data.database

import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM tb_favorite")
    suspend fun getFavoriteUser(): List<UserEntity>

    @Insert
    suspend fun insertFavoriteUser(data: UserEntity)

    @Update
    suspend fun updateFavoriteUser(data: UserEntity)

    @Delete
    suspend fun deleteFavoriteUser(data: UserEntity)
}