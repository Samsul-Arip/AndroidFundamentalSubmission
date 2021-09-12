package com.samsul.githubuser.ui.home.detail

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.samsul.githubuser.data.database.LocalDataSource
import com.samsul.githubuser.data.database.UserEntity
import com.samsul.githubuser.data.model.DataDetailUser
import com.samsul.githubuser.networking.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel : ViewModel() {

    val detailUser = MutableLiveData<DataDetailUser>()

    fun addFavoriteUser(context: Context, id: Int, name: String, image: String, onSuccess: (Boolean) -> Unit ){
        val localDataSource = LocalDataSource(context)
        localDataSource.saveFavoriteUser(UserEntity(id,name,image))
        onSuccess(true)
    }

    fun setUserDetail(username: String) {
        ApiService.endPoint.getDetailUsers(username).enqueue(object : Callback<DataDetailUser> {
            override fun onResponse(
                call: Call<DataDetailUser>,
                response: Response<DataDetailUser>
            ) {
                if (response.isSuccessful) {
                    val responseDetail = response.body()
                    detailUser.postValue(responseDetail)
                }

            }

            override fun onFailure(call: Call<DataDetailUser>, t: Throwable) {
                Log.d("onFailur", t.message!!)
            }

        })
    }

    fun getUserDetail(): LiveData<DataDetailUser> {
        return detailUser
    }


}