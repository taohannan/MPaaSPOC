package com.tutorial.mpaaspoc

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: LoginService by lazy {
        Retrofit.Builder()
            .baseUrl("https://id.azrulhasni.my/realms/Kelichap/protocol/openid-connect/") // <- no `/token` at end
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginService::class.java)
    }
}