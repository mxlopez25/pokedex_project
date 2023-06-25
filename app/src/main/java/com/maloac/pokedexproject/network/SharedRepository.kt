package com.maloac.pokedexproject.network

import com.maloac.pokedexproject.models.PokedexData
import com.maloac.pokedexproject.models.PokedexDataResponse

class SharedRepository {
    suspend fun getAllPokemons(): PokedexDataResponse? {
        val request = NetworkLayer.apiClient.getAllPokemons()

        if (request.isSuccessful) {
            return request.body()!!
        }

        return null
    }

    suspend fun getPokemonByName(name: String): PokedexData? {
        val request = NetworkLayer.apiClient.getPokemonByName(name)

        if (request.isSuccessful) {
            return request.body()!!
        }

        return null
    }

}
