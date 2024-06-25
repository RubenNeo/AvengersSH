package com.example.avengerssh.retrofit

import com.example.avengerssh.data.SuperHeroApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider {


    companion object{
        private fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://superheroapi.com/api/7252591128153666/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun getSuperHeroApiService() : SuperHeroApiService{
            return getRetrofit().create(SuperHeroApiService::class.java)
        }
    }
}