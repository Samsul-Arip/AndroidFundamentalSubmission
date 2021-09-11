package com.samsul.githubuser.ui.home.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.samsul.githubuser.data.DataDetailUser
import com.samsul.githubuser.networking.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel : ViewModel() {

    val detailUser = MutableLiveData<DataDetailUser>()

    fun setUserDetail(username: String) {
        ApiService.endPoint.getDetailUsers(username).enqueue(object : Callback<DataDetailUser>{
            override fun onResponse(
                call: Call<DataDetailUser>,
                response: Response<DataDetailUser>
            ) {
                if(response.isSuccessful){
                    val responseDetail = response.body()
                    detailUser.postValue(responseDetail)
                }

            }

            override fun onFailure(call: Call<DataDetailUser>, t: Throwable) {
                Log.d("onFailur", t.message!!)
            }

        })
    }

    fun getUserDetail(): LiveData<DataDetailUser>{
        return detailUser
    }
}