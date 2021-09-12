package com.samsul.githubuser.data.database

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocalDataSource(context: Context) {

    private val userDatabase = UserDatabase.getDatabase(context)
    private val userDao = userDatabase.userDao()

    fun getFavoriteUser(callback: (List<UserEntity>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            callback(userDao.getFavoriteUser())
        }
    }

    fun saveFavoriteUser(data: UserEntity){
        CoroutineScope(Dispatchers.IO).launch {
            userDao.insertFavoriteUser(data)
        }
    }

    fun updateFavoriteUser(data: UserEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao.updateFavoriteUser(data)
        }
    }

    fun deleteFavoriteUser(data: UserEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao.deleteFavoriteUser(data)
        }
    }
}