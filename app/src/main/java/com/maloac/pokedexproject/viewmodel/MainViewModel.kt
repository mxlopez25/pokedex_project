package com.maloac.pokedexproject.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maloac.pokedexproject.models.PokedexData
import com.maloac.pokedexproject.models.PokedexDataResponse
import com.maloac.pokedexproject.network.SharedRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(): ViewModel() {
    private val repository = SharedRepository()

    private var _requestState = MutableStateFlow<RequestState>(RequestState.Empty)
    val requestState: StateFlow<RequestState> = _requestState

    private var _pokemonDetail = MutableStateFlow(PokedexData())
    val pokedexDetail: StateFlow<PokedexData> = _pokemonDetail

    private var _pokemonList = MutableStateFlow(PokedexDataResponse())
    var pokemonList: StateFlow<PokedexDataResponse> = _pokemonList

    fun getAllPokemons() {
        viewModelScope.launch {
            _requestState.value = RequestState.InProcess
            val response = repository.getAllPokemons()
            if (response != null) {
                Log.d("Response", "${response}")
                _requestState.value = RequestState.Success
                _pokemonList.value = response
            } else {
                _requestState.value = RequestState.Empty
            }

        }
    }

    fun getPokemonByName(name: String?) {
        viewModelScope.launch {
            _requestState.value = RequestState.InProcess
            val response = name?.let {   repository.getPokemonByName(it) }

            if (response != null) {
                Log.d("Detail Response", "$response")
                _requestState.value = RequestState.Success
                _pokemonDetail.value = response
            } else {
                _requestState.value = RequestState.Empty
            }
        }
    }

    sealed class RequestState {
        object Success: RequestState()
        object InProcess: RequestState()
        data class Error(val message: String): RequestState()
        object Empty: RequestState()
    }
}