package com.maloac.pokedexproject.network

import com.maloac.pokedexproject.models.PokedexData
import com.maloac.pokedexproject.models.PokedexDataResponse
import retrofit2.Response

class ApiClient(
    private val retrofitService: DataService
) {

    suspend fun getAllPokemons() : Response<PokedexDataResponse> {
        return retrofitService.getAllPokemons()
    }

    suspend fun getPokemonByName(name: String): Response<PokedexData> {
        return retrofitService.getPokemonByName(name)
    }

}
