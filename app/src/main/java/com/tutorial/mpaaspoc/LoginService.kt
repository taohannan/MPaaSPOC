package com.tutorial.mpaaspoc

import com.tutorial.mpaaspoc.data.TokenResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {
    @FormUrlEncoded
    @POST("token")
    suspend fun login(
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String,
        @Field("username") username: String,
        @Field("password") password: String
    ): TokenResponse
}