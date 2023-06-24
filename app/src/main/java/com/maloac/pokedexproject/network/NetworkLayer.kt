package com.maloac.pokedexproject.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkLayer {
    private val BASE_URL = "https://pokeapi.co/api/v2/"

    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    val retrofit: Retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val retrofitService: DataService by lazy {
        retrofit.create(DataService::class.java)
    }

    val apiClient = ApiClient(retrofitService)
}