package com.samsul.githubuser.ui.home.favorite

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.samsul.githubuser.data.database.LocalDataSource
import com.samsul.githubuser.data.database.UserEntity

class FavoriteViewModel : ViewModel() {

    val listUserFavorite = MutableLiveData<List<UserEntity>>()

    fun getAllFavorite(context: Context) {
        val dataSource = LocalDataSource(context)
        dataSource.getFavoriteUser { result ->
            listUserFavorite.postValue(result)
        }
    }
    fun deleteFavorite(context: Context, data: UserEntity, onSucces: (Boolean) -> Unit) {
        val localDataSource = LocalDataSource(context)
        localDataSource.deleteFavoriteUser(data)
        onSucces(true)
    }
}