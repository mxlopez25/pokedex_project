package com.maloac.pokedexproject.models

data class PokedexDataResponse(
    val count: Int? = null,
    val next: String? = null,
    val previous: String? = null,
    val results: List<ResultsItem>? = null
)
