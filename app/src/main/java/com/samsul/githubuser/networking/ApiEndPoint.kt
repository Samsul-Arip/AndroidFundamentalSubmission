package com.samsul.githubuser.networking

import com.samsul.githubuser.data.DataDetailUser
import com.samsul.githubuser.data.DataFollow
import com.samsul.githubuser.data.DataSearchUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndPoint {

    @GET("search/users")
    //@Headers("Authorization: token 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")
    fun searchUser(
        @Query("q") username: String,
    ): Call<DataSearchUser.responseUser>


    @GET("users/{username}")
    //@Headers("Authorization: token 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")
    fun getDetailUsers(
        @Path("username") username: String
    ) : Call<DataDetailUser>

    @GET("users/{username}/followers")
    //@Headers("Authorization: token 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")
    fun getFollowerUsers(
        @Path("username") username: String
    ): Call<ArrayList<DataFollow>>

    @GET("users/{username}/following")
    //@Headers("Authorization: token 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")
    fun getFollowingUsers(
        @Path("username") username: String
    )


}