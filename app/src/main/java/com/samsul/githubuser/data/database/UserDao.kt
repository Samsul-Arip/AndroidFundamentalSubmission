package com.samsul.githubuser.data.database

import android.database.Cursor
import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM tb_favorite")
    suspend fun getFavoriteUser(): List<UserEntity>

    @Query("SELECT * FROM tb_favorite")
    fun findAll(): Cursor

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteUser(data: UserEntity)

    @Update
    suspend fun updateFavoriteUser(data: UserEntity)

    @Delete
    suspend fun deleteFavoriteUser(data: UserEntity)


}