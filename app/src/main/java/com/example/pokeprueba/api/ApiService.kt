package com.example.pokeprueba.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("pokemon")
    fun getPokemonList(@Query("limit") limit: Int, @Query("offset") offset: Int): Call<PokeResponse>

    @GET("pokemon/{id}")
    fun getPokemon(@Path("id") id: Int): Call<Pokemon>

    @GET("pokemon-species/{id}")
    fun getPokemonSpecies(@Path("id") id: Int): Call<Pokemon>
}