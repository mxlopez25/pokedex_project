package com.maloac.pokedexproject.helpers

import android.content.Context
import android.content.SharedPreferences

class MainActivityPrefs(context: Context) {
    private val MAIN_ACTIVITY_CACHE = "MAIN_ACTIVITY_CACHE"
    private val POKEMON_LIST_CACHE = "POKEMON_LIST_CACHE"

    private val preferences: SharedPreferences = context.getSharedPreferences(MAIN_ACTIVITY_CACHE , Context.MODE_PRIVATE)

    var pokemonList: String?
        get() = preferences.getString(POKEMON_LIST_CACHE, null)
        set(value) = preferences.edit().putString(POKEMON_LIST_CACHE, value).apply()
}