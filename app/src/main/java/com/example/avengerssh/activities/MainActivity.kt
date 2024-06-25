package com.example.avengerssh.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.avengerssh.Adapter.SuperheroAdapter
import com.example.avengerssh.R
import com.example.avengerssh.activities.details_superheroactivity.Companion.EXTRA_ID
import com.example.avengerssh.databinding.ActivityMainBinding
import com.example.avengerssh.retrofit.RetrofitProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: SuperheroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter = SuperheroAdapter(){name -> navigateToDetails(-1)

        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)

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
                    searchByName(newText)
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
                val apiService = RetrofitProvider.getSuperHeroApiService()
                val result = apiService.findSuperheroesByName(query)
                //Llamada al hilo principal
                runOnUiThread {
                    if (result.results != null) {
                        adapter.updateData(result.results)
                    } else {
                        adapter.updateData(emptyList()) // Para manejar el caso en que results es null
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun navigateToDetails(id:Int){
        val intent = Intent (this, details_superheroactivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }
}
