package com.maloac.pokedexproject.network

import com.maloac.pokedexproject.models.PokedexData
import com.maloac.pokedexproject.models.PokedexDataResponse
import retrofit2.Response

class ApiClient(
    private val retrofitService: DataService
) {

    suspend fun getAllPokemons() : DataResponseHandler<PokedexDataResponse> {
        return safeApiCall {
            retrofitService.getAllPokemons()
        }
    }

    suspend fun getPokemonByName(name: String): DataResponseHandler<PokedexData> {
        return safeApiCall {
            retrofitService.getPokemonByName(name)
        }
    }


    private inline fun<T> safeApiCall(apiCall: () -> Response<T>): DataResponseHandler<T> {
        return try {
            DataResponseHandler.success(apiCall.invoke())
        } catch (e: Exception) {
            DataResponseHandler.failure<T>(e)
        }
    }
}
