package com.maloac.pokedexproject.network

import com.maloac.pokedexproject.models.PokedexData
import com.maloac.pokedexproject.models.PokedexDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DataService {
    @GET("pokemon")
    suspend fun getAllPokemons(
        @Query("offset")offset: String = "0",
        @Query("limit")limit: String = "151"
    ): Response<PokedexDataResponse>

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(@Path("name")path: String): Response<PokedexData>


}
