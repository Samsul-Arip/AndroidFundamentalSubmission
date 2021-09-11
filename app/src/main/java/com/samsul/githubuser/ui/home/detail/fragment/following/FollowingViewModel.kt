package com.samsul.githubuser.ui.home.detail.fragment.following

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.samsul.githubuser.data.DataFollow
import com.samsul.githubuser.networking.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel : ViewModel() {

    val listFollowing = MutableLiveData<ArrayList<DataFollow>>()

    fun setListFollowing(username: String) {
        ApiService.endPoint.getFollowingUsers(username).enqueue(object :
            Callback<ArrayList<DataFollow>> {
            override fun onResponse(
                call: Call<ArrayList<DataFollow>>,
                response: Response<ArrayList<DataFollow>>
            ) {
                val responseFollow = response.body()
                listFollowing.postValue(responseFollow)
            }

            override fun onFailure(call: Call<ArrayList<DataFollow>>, t: Throwable) {
                Log.d("onFailur", t.message!!)
            }

        })
    }

    fun getListFollowing(): LiveData<ArrayList<DataFollow>> {
        return listFollowing
    }

}