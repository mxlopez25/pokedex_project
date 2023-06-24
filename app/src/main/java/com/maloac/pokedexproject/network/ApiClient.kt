package com.maloac.pokedexproject.network

import com.maloac.pokedexproject.models.PokedexDataResponse
import retrofit2.Response

class ApiClient(
    private val retrofitService: DataService
) {

    suspend fun getPokemons() : Response<PokedexDataResponse> {
        return retrofitService.getAllPokemons()
    }

}
