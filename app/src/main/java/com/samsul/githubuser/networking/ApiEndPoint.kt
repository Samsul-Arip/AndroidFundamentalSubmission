package com.samsul.githubuser.networking

import com.samsul.githubuser.data.DataDetailUser
import com.samsul.githubuser.data.DataSearchUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndPoint {

    @GET("search/users")
    @Headers("Authorization: token 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")
    fun searchUser(
        @Query("q") username: String,
    ): Call<DataSearchUser.responseUser>


    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUsers(
        @Path("username") username: String
    ) : Call<DataDetailUser>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowerUsers(
        @Path("username") username: String
    )

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowingUsers(
        @Path("username") username: String
    )


}