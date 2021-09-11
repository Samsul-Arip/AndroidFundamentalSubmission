package com.samsul.githubuser.ui.home.detail.fragment.followers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.samsul.githubuser.data.DataFollow
import com.samsul.githubuser.data.DataSearchUser
import com.samsul.githubuser.networking.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel : ViewModel() {

    val listFollowers = MutableLiveData<ArrayList<DataFollow>>()

    fun setListFollowers(username: String) {
        ApiService.endPoint.getFollowerUsers(username).enqueue(object : Callback<ArrayList<DataFollow>>{
            override fun onResponse(
                call: Call<ArrayList<DataFollow>>,
                response: Response<ArrayList<DataFollow>>
            ) {
                val responseFollow = response.body()
                listFollowers.postValue(responseFollow)
            }

            override fun onFailure(call: Call<ArrayList<DataFollow>>, t: Throwable) {
                Log.d("onFailur", t.message!!)
            }

        })
    }

    fun getListFollowers(): LiveData<ArrayList<DataFollow>>{
        return listFollowers
    }

}