package com.example.avengerssh.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.avengerssh.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class details_superheroactivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_superheroactivity)
         intent.getStringExtra(EXTRA_ID).orEmpty()

    }


}