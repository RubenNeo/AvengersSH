package com.example.avengerssh.data

import retrofit2.http.GET
import retrofit2.http.Path

interface SuperHeroApiService {
    @GET("search/{name}")
    suspend fun findSuperheroesByName(@Path("name") query: String) : SuperheroListResponse
    @GET ("{id}")
    suspend fun GetSuperHeroesId(@Path("id") id: Int) : SuperHeroDetailResponse
}

