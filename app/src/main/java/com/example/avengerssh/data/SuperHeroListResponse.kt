package com.example.avengerssh.data

import com.google.gson.annotations.SerializedName

data class SuperheroListResponse(
    @SerializedName("response") val response: String,
    @SerializedName("results-for") val resultsFor: String,
    @SerializedName("results") val results: List<SuperheroResponse>
) {
}


data class SuperheroResponse(
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: Image,
    @SerializedName("id") val id: Int,



) {
}


data class Image(
    @SerializedName("url") val url: String
) {
}
