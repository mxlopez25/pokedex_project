package com.maloac.pokedexproject.models

data class PokedexData(
    val id: Int? = null,
    val order: Int? = null,
    val name: String? = null,
    val height: Int? = null,
    val weight: Int? = null,
    val baseExperience: Int? = null,
    val isDefault: Boolean? = null,
    val species: Spicies? = null,
    val locationAreaEncounters: String? = null,

    val abilities: List<Ability>? = null,
    val forms: List<Form>? = null,
    val gameIndeces: List<GameIndex>? = null,
    val moves: List<Move>? = null,
    val stats: List<Stat>? = null,
    val types: List<Type>? = null
) {
    data class Ability(
        val name: String,
        val url: String,
        val isHidden: Boolean,
        val slot: Int
    )

    data class Form(
        val name: String,
        val url: String
    )

    data class Version(
        val name: String,
        val url: String
    )

    data class GameIndex(
        val gameIndex: Int,
        val version: Version
    )

    data class Move(
        val name: String,
        val url: String
    )

    data class Spicies(
        val name: String,
        val url: String
    )

    data class Stat(
        val name: String,
        val url: String,
        val effort: Int,
        val baseStat: Int
    )

    data class Type(
        val slot: Int,
        val name: String,
        val url: String
    )
}
