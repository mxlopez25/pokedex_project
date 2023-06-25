package com.maloac.pokedexproject.network

import com.maloac.pokedexproject.models.PokedexDataResponse

class SharedRepository {
    suspend fun getAllPokemons(): PokedexDataResponse? {
        val request = NetworkLayer.apiClient.getAllPokemons()

        if (request.isSuccessful) {
            return request.body()!!
        }

        return null
    }

}
