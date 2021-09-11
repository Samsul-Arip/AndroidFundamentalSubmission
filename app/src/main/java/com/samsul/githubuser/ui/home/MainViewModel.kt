package com.samsul.githubuser.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.samsul.githubuser.data.DataSearchUser
import com.samsul.githubuser.networking.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    val listUsers = MutableLiveData<ArrayList<DataSearchUser.dataUser>>()

    fun setSearchUser(username: String) {
        ApiService.endPoint.searchUser(username).enqueue(object: Callback<DataSearchUser.responseUser>{
            override fun onResponse(
                call: Call<DataSearchUser.responseUser>,
                response: Response<DataSearchUser.responseUser>
            ) {
                if(response.isSuccessful) {
                    val responseSearch = response.body()?.listItems
                    listUsers.postValue(responseSearch)
                }
            }

            override fun onFailure(call: Call<DataSearchUser.responseUser>, t: Throwable) {
                Log.d("onFailur", t.message!!)
            }

        })
    }

    fun getSearchUser(): LiveData<ArrayList<DataSearchUser.dataUser>>{
        return listUsers
    }
}