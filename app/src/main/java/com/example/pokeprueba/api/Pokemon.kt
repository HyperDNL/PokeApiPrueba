package com.example.pokeprueba.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Pokemon(
    @Expose @SerializedName("id") val id: Int,
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("base_experience") val baseExperience: Int,
    @Expose @SerializedName("height") val height: Int,
    @Expose @SerializedName("weight") val weight: Int,
    @Expose @SerializedName("sprites") val sprites: Sprites,
    @Expose @SerializedName("types") val types: List<Type>,
    @Expose @SerializedName("flavor_text_entries") val flavorTextEntries: List<FlavorTextEntry>,

    var spanishFlavorTextEntries: List<String> = emptyList()
)

data class FlavorTextEntry(
    @Expose @SerializedName("flavor_text") val flavorText: String,
    @Expose @SerializedName("language") val language: Language
)

data class Language(
    @Expose @SerializedName("name") val name: String,
)

data class Sprites(
    @Expose @SerializedName("front_default") val frontDefault: String?,
)

data class PokemonResult(
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("url") val url: String
)


data class Type(
    @Expose @SerializedName("slot") val slot: Int,
    @Expose @SerializedName("type") val type: TypeInfo
)

data class TypeInfo(
    @Expose @SerializedName("name") val name: String,
)
