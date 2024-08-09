package com.app.suitmedia.retrofit

import com.app.suitmedia.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/api/users")
    suspend fun getUsers(@Query("page") page: Int, @Query("per_page") perPage: Int): UserResponse
}