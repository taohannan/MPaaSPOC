package com.tutorial.mpaaspoc.network

import com.tutorial.mpaaspoc.data.TokenResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path

interface KeyCloakApi {

    @FormUrlEncoded
    @POST("realms/{realm}/protocol/openid-connect/token")
    suspend fun login(
        @Path("realm") realm: String,
        @Field("grant_type") grantType: String = "password",
        @Field("client_id") clientId: String,
        @Field("username") username: String,
        @Field("password") password: String
    ): TokenResponse
}