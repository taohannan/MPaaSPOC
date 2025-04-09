package com.tutorial.mpaaspoc.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object KeyCloakService {

    private const val BASE_URL = "https://id.azrulhasni.my/"

    val api: KeyCloakApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(KeyCloakApi::class.java)
    }
}