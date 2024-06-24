package com.example.avengerssh.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.avengerssh.data.SuperheroResponse
import com.example.avengerssh.databinding.ItemSuperheroBinding


class SuperheroAdapter (private var dataSet: List<SuperheroResponse> = emptyList()) : RecyclerView.Adapter<SuperheroViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroViewHolder {
        val binding = ItemSuperheroBinding.inflate(LayoutInflater.from(parent.context))
        return SuperheroViewHolder(binding)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: SuperheroViewHolder, position: Int) {
        holder.render(dataSet[position])
    }

    fun updateData(dataSet: List<SuperheroResponse>) {
        this.dataSet = dataSet
        notifyDataSetChanged()
    }
}

