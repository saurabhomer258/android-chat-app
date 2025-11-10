package com.example.chatflow.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

data class UserDto(val id: Int, val firstName: String, val lastName: String, val image: String)

data class UsersResponse(val users: List<UserDto>)

interface ApiService {
    @GET("users")
    suspend fun getUsers(): UsersResponse
}
