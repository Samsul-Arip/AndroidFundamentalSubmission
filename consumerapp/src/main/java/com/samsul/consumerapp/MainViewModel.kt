package com.samsul.consumerapp

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val list = MutableLiveData<ArrayList<DataSearchUser>>()

    fun setFavoriteUser(context: Context) {
        val cursor = context.contentResolver.query(
            DatabaseContract.FavoriteUserColums.CONTENT_URI, null, null, null, null
        )
        val listConverted = MappingHelper.mapCursorToArrayList(cursor)
        list.postValue(listConverted)
    }

    fun getFavoriteUser(): LiveData<ArrayList<DataSearchUser>> {
        return list
    }
}