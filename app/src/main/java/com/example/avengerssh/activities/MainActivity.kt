package com.example.avengerssh.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.avengerssh.Adapter.SuperheroAdapter
import com.example.avengerssh.R
import com.example.avengerssh.data.SuperHeroApiService
import com.example.avengerssh.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding

    lateinit var adapter: SuperheroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = SuperheroAdapter()

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)

            searchByName("super")
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_activity_main, menu)

        val searchViewItem = menu.findItem(R.id.menu_search)
        val searchView = searchViewItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {

                }
                return true
            }
        })

        return true
    }

    private fun searchByName(query: String) {
        // Llamada en segundo plano
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val apiService = getRetrofit().create(SuperHeroApiService::class.java)
                val result = apiService.findSuperheroesByName(query)
                //Llamada al hilo pricipal
                runOnUiThread {
                    adapter.updateData(result.results)
                }

                //Log.i("HTTP", "${result.results}")

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        /*val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()*/

        return Retrofit.Builder()
            .baseUrl("https://superheroapi.com/api/7252591128153666/")
            //.client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}