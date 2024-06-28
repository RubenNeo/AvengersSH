package com.example.avengerssh.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.Toast
import com.example.avengerssh.R
import com.example.avengerssh.data.PowerStatsResponse
import com.example.avengerssh.data.SuperHeroApiService
import com.example.avengerssh.data.SuperHeroDetailResponse
import com.example.avengerssh.databinding.ActivityDetailBinding
import com.example.avengerssh.databinding.ActivityMainBinding
import com.example.avengerssh.retrofit.RetrofitProvider
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsSuperHeroActivity : AppCompatActivity() {


    private lateinit var binding: ActivityDetailBinding

    private lateinit var superhero: SuperHeroDetailResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        val id = intent.getIntExtra("SUPERHERO_ID", -1)
        GetById(id)
        //para que al clickar en el boton del heroe me salga la animacion a escala del ImageView
        val scaleAnimation = ScaleAnimation(
            0f, 1f, // Escala de X: de 0% a 100%
            0f, 1f, // Escala de Y: de 0% a 100%
            Animation.RELATIVE_TO_SELF, 0.5f, // Pivot X: en el centro de la vista
            Animation.RELATIVE_TO_SELF, 0.5f  // Pivot Y: en el centro de la vista
        ).apply {
            duration = 2000 // Duración de la animación en milisegundos
            fillAfter = true // Mantener el estado final después de la animación
        }

        // Iniciar la animación
        binding.ivSuperhero.startAnimation(scaleAnimation)
    }


    private fun loadData() {
        binding.tvSuperHeroname.text = superhero.name
        binding.tvSuperheroRealName.text = superhero.biography.fullname
        binding.tvPublisher.text = superhero.biography.publisher
        binding.Afiliation.text = superhero.connections.affiliation
        Picasso.get().load(superhero.image.url).into(binding.ivSuperhero)


        prepareStats(superhero.powerstats)

        updateCompanyLogo(superhero.biography.publisher)


    }

    private fun prepareStats(powerstats: PowerStatsResponse) {
        //para determinar los valores de cada uno de los powerstat al pasarlos a Int
        updateHeight(binding.viewCombat, powerstats.combat)
        updateHeight(binding.viewDurability, powerstats.durability)
        updateHeight(binding.viewPower, powerstats.power)
        updateHeight(binding.viewSpeed, powerstats.speed)
        updateHeight(binding.viewIntelligence, powerstats.intelligence)
        updateHeight(binding.viewStrength, powerstats.strength)

    }

    private fun updateHeight(view: View, stat: Int) {
        //El height aumenta segun el numero que tenga puesto la api en su superheroe
        val params = view.layoutParams
        params.height = stat
        view.layoutParams = params


    }


    private fun GetById(id: Int) {
        // Llamada en segundo plano
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val apiService = getRetrofit().create(SuperHeroApiService::class.java)
                val result = apiService.GetSuperHeroesId(id)

                superhero = result

                runOnUiThread {
                    loadData()


                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://superheroapi.com/api/7252591128153666/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun updateCompanyLogo(publisher: String) {
        val logoResId = when (publisher) {
            "Marvel Comics" -> R.drawable.marvel_studios
            "DC Comics" -> R.drawable.dc_comics
            else -> null
        }

        if (logoResId != null) {
            binding.logoSuperheroes.setImageResource(logoResId)
            binding.logoSuperheroes.visibility = View.VISIBLE
        } else {
            binding.logoSuperheroes.visibility = View.GONE
        }
    }
}

