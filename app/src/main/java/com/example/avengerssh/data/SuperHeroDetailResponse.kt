package com.example.avengerssh.data

import com.google.gson.TypeAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter


// Data class to represent the detailed response for a superhero
data class SuperHeroDetailResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("powerstats") val powerstats: PowerStatsResponse,
    @SerializedName("image") val image: SuperheroImageDetailResponse,
    @SerializedName("biography") val biography: SuperheroBiographyResponse,
    @SerializedName("connections") val connections: Connections
)

// data class para representar las estadisticas del heroe
data class PowerStatsResponse(
    @JsonAdapter(IntegerAdapter::class) @SerializedName("intelligence") val intelligence: Int,
    @JsonAdapter(IntegerAdapter::class) @SerializedName("strength") val strength: Int,
    @JsonAdapter(IntegerAdapter::class) @SerializedName("speed") val speed: Int,
    @JsonAdapter(IntegerAdapter::class) @SerializedName("durability") val durability: Int,
    @JsonAdapter(IntegerAdapter::class) @SerializedName("power") val power: Int,
    @JsonAdapter(IntegerAdapter::class) @SerializedName("combat") val combat: Int,
)


// Data class para la informacion del superheroe en details
data class SuperheroImageDetailResponse(@SerializedName("url") val url: String)

// Data class biografia del superheroe
data class SuperheroBiographyResponse(
    @SerializedName("full-name") val fullname: String,
    @SerializedName("publisher") val publisher: String
)

class IntegerAdapter : TypeAdapter<Int>() {
    override fun write(out: JsonWriter?, value: Int?) {
        out?.value(value)
    }

    override fun read(`in`: JsonReader?): Int {
        if (`in` != null) {
            val value: String = `in`.nextString()
            if (value != "null") {
                return value.toInt()
            }
        }
        return 0
    }


}

data class Connections(

    @SerializedName("group-affiliation") val affiliation : String,
    @SerializedName("relatives") val relatives: String

)

