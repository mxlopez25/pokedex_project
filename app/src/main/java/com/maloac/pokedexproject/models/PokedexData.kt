package com.maloac.pokedexproject.models

import com.squareup.moshi.Json

data class PokedexData(
    val id: Int? = null,
    val order: Int? = null,
    val name: String? = "",
    val height: Int? = null,
    val weight: Int? = null,
    val baseExperience: Int? = null,
    val isDefault: Boolean? = false,
    val species: Spicies? = null,
    val locationAreaEncounters: String? = null,

    val abilities: List<Ability>? = emptyList(),
    val forms: List<Form>? = emptyList(),
    val gameIndeces: List<GameIndex>? = emptyList(),
    @Json(name="moves")
    val moves: List<Moves>? = emptyList(),
    val stats: List<Stat>? = emptyList(),
    val types: List<Type>? = emptyList()
) {
    data class Ability(
        val name: String? = "",
        val url: String? = "",
        val isHidden: Boolean = false,
        val slot: Int = 0
    )

    data class Form(
        val name: String? = "",
        val url: String? = ""
    )

    data class Version(
        val name: String? = "",
        val url: String? = ""
    )

    data class GameIndex(
        val gameIndex: Int = 0,
        val version: Version? = Version()
    )

    data class Moves(
        val move: Move? = null,
        val versionGroupDetails: VersionGroupDetails? = null
    )

    data class Move(
        val name: String? = "",
        val url: String? = ""
    )

    data class VersionGroupDetails(
        val levelLearnedAt: Int? = 0,
        val moveLearnMethod: MoveLearnMethod? = null,
        val versionGroup: VersionGroup
    )

    data class VersionGroup(
        val name: String? = "",
        val url: String? = ""
    )

    data class MoveLearnMethod(
        val name: String? = "",
        val url: String? = ""
    )

    data class Spicies(
        val name: String? = "",
        val url: String? = ""
    )

    data class Stat(
        val name: String? = "",
        val url: String? = "",
        val effort: Int = 0,
        val baseStat: Int = 0
    )

    data class Type(
        val slot: Int = 0,
        val name: String? = "",
        val url: String? = null
    )
}

