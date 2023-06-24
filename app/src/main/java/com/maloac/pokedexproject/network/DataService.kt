package com.maloac.pokedexproject.network

import com.maloac.pokedexproject.models.PokedexDataResponse
import retrofit2.Response
import retrofit2.http.GET

interface DataService {
    @GET("pokemon")
    suspend fun getAllPokemons(): Response<PokedexDataResponse>

}
