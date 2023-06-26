package com.maloac.pokedexproject.network

import com.maloac.pokedexproject.models.PokedexData
import com.maloac.pokedexproject.models.PokedexDataResponse

class SharedRepository {
    suspend fun getAllPokemons(): PokedexDataResponse? {
        val request = NetworkLayer.apiClient.getAllPokemons()

        if(request.failed) {
           return null
        }

        if (!request.isSuccessful) {
            return null
        }

        return request.body
    }

    suspend fun getPokemonByName(name: String): PokedexData? {
        val request = NetworkLayer.apiClient.getPokemonByName(name)

        if(request.failed) {
            return null
        }

        if (!request.isSuccessful) {
            return null
        }

        return request.body
    }

}
